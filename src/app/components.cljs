(ns app.components
  (:require [reagent.core :as reagent]
            [reagent.dom :as r.dom]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [app.data :as data]
            ["highlight.js" :as hljs]
            [app.nightwind :refer [dark-light-button]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialization
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce blog-posts (reagent/atom nil))

(data/store-posts-data blog-posts)

(defn- highlight-code-block [node]
  (if (-> node
          r.dom/dom-node
          (.querySelector "pre code")
          nil?)
    nil
    (doseq [block (.querySelectorAll (r.dom/dom-node node) "pre code")]
      (.highlightElement hljs block))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Components
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn navbar
  []
  [:nav {:class ["bg-black" "bg-opacity-50"
                 "py-2" "sm:px-8" "text-lg" "font-serif"
                 "text-gray-50" "grid"
                 "grid-flow-col" "grid-cols-2"
                 "underline" "italic"]}
   [:div {:class ["p-2"]}
    [:a {:href (rfe/href ::home) :title "Home"} "Home"]]
   [:div {:class ["p-2" "justify-end" "inline-flex"]}
    [:div {:class ["px-2"]}
     [:a {:href (rfe/href ::blog) :title "Blog"} "Blog"]]
    [:div {:class ["px-2"]}
     [:a {:href (rfe/href ::contact) :title "Contact"} "Contact"]]
    [:div {:class ["px-2"]}
     [dark-light-button]]]])

(defn display-date
  [date-string]
  (let [date (js/Date. date-string)]
    (. date (toLocaleDateString))))

(defn this-year
  []
  (let [now (js/Date.)]
    (.getFullYear now)))

(defn blog-post-preview
  [blog-post]
  [:div {:class ["border-2" "rounded" "border-gray-500"
                 "m-2" "pt-2" "pb-4" "px-4"
                 "bg-gray-200"]}
   [:a {:href (rfe/href ::post {:id (-> blog-post :meta :id)})}
    [:h2 {:class ["text-2xl"]} (-> blog-post :meta :title)]
    [:p {:class ["text-xs py-0.5 text-gray-500"]}
     (display-date (-> blog-post :meta :date))]
    [:p {:class ["text-sm" "overflow-ellipsis" "line-clamp-5"
                 "text-gray-600"]}
     (-> blog-post :meta :subtitle)]]])

(defn blog-post-main-view
  [blog-post]
  (reagent/create-class
    {:component-did-mount highlight-code-block
     :reagent-render
     (fn [blog-post]
       [:article {:class ["m-2" "mt-6" "pt-2"
                          "pb-4" "px-2" "sm:px-8"]}
        [:div
         [:h1 {:class ["font-bold text-4xl"]} (-> blog-post :meta :title)]
         [:p {:class ["pb-4" "text-gray-500"]}
          (display-date (-> blog-post :meta :date))]
         [:article {:class ["prose" "prose-sm" "sm:prose"
                            "lg:prose-lg" "mx-auto"]
                    :dangerouslySetInnerHTML
                    {:__html (:content blog-post)}}]]])}))

(defn generic-link
  [link message & mail?]
  (let [link (cond (not (nil? mail?)) (str "mailto: " link)
                   :else              link)]
    [:a {:href   link
         :target "_blank"
         :class  ["text-blue-500" "hover:text-blue-600"]} message]))

(defn license
  []
  [:footer {:class ["p-2" "text-xs" "text-center" "self-center"]}
   [:p "West's "
    (generic-link
      "https://github.com/wildwestrom/mysite" "static site generator")
    " is licensed"
    [:br {:class ["xs:hidden" "block"]}]
    " under the "
    (generic-link
      "https://www.gnu.org/licenses/agpl-3.0.html" "GNU AGPL License")
    "." [:br]
    "The rest is my own original work"
    [:br {:class ["xs:hidden" "block"]}]
    " unless otherwise specified."
    [:br]
    "Copyright © "
    (this-year) " "
    (:author data/global-config) " "
    [:br {:class ["xs:hidden" "block"]}]
    (generic-link (:email data/global-config)
                  (:email data/global-config) true)]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pages
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn home-page
  []
  [:div {:class ["flex" "flex-col" "flex-grow"]}
   [:div {:class ["flex-grow" "flex" "justify-center" "items-center"]}
    [:h2 {:class ["text-5xl" "p-4" "italic"]}
     "Welcome," [:br {:class ["block" "sm:hidden"]}] " to my site!"]]])

(defn blog-preview-page
  []
  [:div {:class ["max-w-prose" "self-center" "pt-2"
                 "flex" "flex-col" "flex-initial" "items-stretch"]}
   (for [blog-post @blog-posts]
     ^{:key (-> blog-post :meta :id)}
     [blog-post-preview blog-post])])

(defn blog-post-page
  []
  (let [id   (->> @data/match :parameters :path :id)
        post (first (filter #(= id (-> % :meta :id)) @blog-posts))]
    [:div
     [blog-post-main-view post]
     [license]]))

(defn contact-page
  []
  [:div {:class ["flex-grow" "flex" "flex-col" "items-stretch"]}
   [:div {:class ["flex-grow" "self-center" "flex" "flex-col"]}
    [:div {:class ["p-4" "flex" "flex-col" "flex-grow" "justify-center"]}
     [:h2 {:class ["pb-4" "text-2xl"]} "You seem pretty sweet." [:br]
      "We should get lunch sometime."]
     [:ul
      [:li {:class ["p-2"]}
       (generic-link (:email data/global-config)
                     (:email data/global-config) true)]
      [:li {:class ["p-2"]}
       (generic-link (:github data/global-config)
                     (:github data/global-config))]]]]
   [license]])

(defn app
  []
  [:div {:class ["text-gray-800" "bg-gray-50"
                 "subpixel-antialiased" "min-h-screen"
                 "flex" "flex-col"]}
   [navbar]
   (if @data/match
     (let [view (:view (:data @data/match))]
       [view @data/match])
     (.log js/console
           (str "Match not found.\n `(:data @data/match)`:"
                (:data @data/match))))])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::home
     :view home-page}]

   ["/blog"
    {:name ::blog
     :view blog-preview-page}]

   ["/blog/:id"
    {:name       ::post
     :view       blog-post-page
     :parameters {:path {:id string?}}}]

   ["/contact"
    {:name ::contact
     :view contact-page}]])

(def router
  (rf/router routes))
