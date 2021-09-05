(ns app.components
  (:require ["@fortawesome/fontawesome-svg-core" :as fontawesome]
            ["@fortawesome/free-brands-svg-icons" :as fab]
            ["@fortawesome/free-solid-svg-icons" :as fas]
            ["@fortawesome/react-fontawesome" :refer [FontAwesomeIcon]]
            ["@headlessui/react" :refer [Transition]]
            ["highlight.js" :as hljs]
            [app.data :as data]
            [app.nightwind :refer [dark-light-button]]
            [clojure.string :as string]
            [reagent.core :as reagent]
            [reagent.dom :as r.dom]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialization
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce blog-posts (reagent/atom nil))

(data/store-posts-data blog-posts)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Components
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn navbar
  []
  [:nav {:class ["bg-gray-300" #_"text-gray-700"
                 "py-2" "sm:px-8" "text-lg" "font-serif"
                 "grid" "grid-flow-col" "grid-cols-2"
                 "underline" "italic"]}
   [:div {:class ["p-2"]}
    [:a {:href (rfe/href :app.router/home) :title "Home"} "Home"]]
   [:div {:class ["p-2" "justify-end" "inline-flex"]}
    [:div {:class ["px-2"]}
     [:a {:href (rfe/href :app.router/blog) :title "Blog"} "Blog"]]
    [:div {:class ["px-2"]}
     [:a {:href (rfe/href :app.router/about) :title "About"} "About"]]
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
   [:a {:href (rfe/href :app.router/post {:id (-> blog-post :meta :id)})}
    [:h2 {:class ["text-2xl"]} (-> blog-post :meta :title)]
    [:p {:class ["text-xs py-0.5 text-gray-500"]}
     (display-date (-> blog-post :meta :date))]
    [:p {:class ["text-sm" "overflow-ellipsis" "line-clamp-5"
                 "text-gray-600"]}
     (-> blog-post :meta :subtitle)]]])

(defn- highlight-code [node]
  (when (.querySelector (r.dom/dom-node node) "pre code")
    (-> (r.dom/dom-node node)
        (.querySelectorAll "pre code")
        array-seq
        first
        hljs/highlightElement)
    #_(doseq [code-block (array-seq (.querySelectorAll (r.dom/dom-node node) "pre code"))]
        (hljs/highlightElement code-block))))

(defn blog-post-main-view
  [blog-post]
  (reagent/create-class
   {:component-did-mount
    highlight-code
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
  [link text & {:keys [mail]}]
  [:a {:href   (if mail
                 (str "mailto: " link)
                 link)
       :target "_blank"
       :class  ["text-blue-500" "hover:text-blue-600"]} text])

(defn license
  []
  [:footer {:class ["p-2" "text-xs" "text-center" "self-center"]}
   [:p (generic-link
        "https://github.com/wildwestrom/mysite" "This webpage")
    " is licensed"
    [:br {:class ["xs:hidden" "block"]}]
    " under the "
    (generic-link
     "https://www.gnu.org/licenses/agpl-3.0.html" "GNU AGPL License")
    "." [:br]
    "Copyright © "
    (this-year) " "
    (:author data/global-config) " "
    [:br {:class ["xs:hidden" "block"]}]
    (generic-link (:email data/global-config)
                  (:email data/global-config) :mail true)]])

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

(fontawesome/library.add fas/fas)

(defn icon-link [link text icon & {:keys [mail]}]
  [:li {:class ["p-2"]}
   [:a {:href   (if mail
                  (str "mailto: " link)
                  link)
        :target "_blank"}
    [:span {:class ["text-blue-600" "hover:text-blue-700"
                    "gap-x-2" "flex" "items-center"]}
     [:> FontAwesomeIcon {:icon icon
                          :class "fa-fw"}]
     [:p text]]]])

(defn copy-text [text icon tooltip]
  (let [showing? (reagent/atom false)]
    (fn []
      [:li {:class ["p-2"]}
       [:> Transition
        {:id "text-copy-indicator"
         :show @showing?
         :enter "transition-opacity duration-75"
         :enter-from "opacity-0"
         :enter-to "opacity-100"
         :leave "transition-opacity duration-300"
         :leave-from "opacity-100"
         :leave-to "opacity-0"
         :class ["border-2" "rounded-lg" "p-1" "absolute"
                 "text-black" "bg-blue-50"
                 "transform" "-translate-y-10"]}
        "Copied to Clipboard!"]
       [:div {:on-click
              (fn []
                (letfn [(toggle [] (swap! showing? not))]
                  (js/navigator.clipboard.writeText text)
                  (toggle)
                  (js/setTimeout toggle 500)))
              :title (string/join " " [tooltip "Click to copy."])
              :class "cursor-pointer"}
        [:span {:class ["text-blue-600" "hover:text-blue-700"
                        "gap-x-2" "flex" "items-center"]}
         [:> FontAwesomeIcon {:icon icon
                              :class "fa-fw"}]
         [:p {:class ["max-w-[20ch]" "overflow-hidden" "overflow-ellipsis"]}
          text]]]])))

(defn about-page
  []
  [:div {:class ["max-w-prose" "self-center" "p-2"
                 "flex" "flex-col" "flex-grow"
                 "items-center" "justify-between"]}
   [:div {:class ["flex" "flex-col" ""]}
    [:h1 {:class ["py-4" "text-3xl"
                  "font-bold"]}
     "Contact"]
    [:h2 {:class ["italic" "pb-2"]}
     "You seem pretty sweet." [:br]
     "We should get lunch sometime."]
    [:ul
     [icon-link (:email data/global-config)
      (:email data/global-config)
      fas/faEnvelope
      :mail true]
     [icon-link (:github data/global-config)
      "wildwestrom"
      fab/faGithub]
     [icon-link (:linkedin data/global-config)
      "c-westrom"
      fab/faLinkedin]
     [copy-text (:discord data/global-config)
      fab/faDiscord "Discord username;"]]
    [:h1 {:class ["py-4" "text-3xl"
                  "font-bold"]}
     "Funding"]
    [:h2 {:class ["italic" "pb-2"]}
     "Because every site needs" [:br]
     "a \"give me money\" button."]
    [:ul
     [copy-text (:monero data/global-config)
      fab/faMonero "Monero wallet;"]]]
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
