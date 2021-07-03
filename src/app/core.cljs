(ns app.core
  (:require [clojure.string]
            [reagent.core :as r]
            [reagent.dom :as r.dom]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.coercion.spec :as rss]
            [app.data :as data :refer [data]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; State
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce match (r/atom nil))

(defonce blog-posts (r/atom nil))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Components
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn navbar
  []
  [:nav {:class '[bg-black bg-opacity-50
                  p-2 text-lg font-serif
                  text-gray-50 dark:text-gray-300
                  grid grid-flow-col grid-cols-2
                  underline italic]}
   [:div {:class '[p-2]}
    [:a {:href (rfe/href ::home) :title "Home"} "Home"]]
   [:div {:class '[p-2 justify-end inline-flex]}
    [:div {:class '[px-2]}
     [:a {:href (rfe/href ::blog) :title "Blog"} "Blog"]]
    [:div {:class '[px-2]}
     [:a {:href (rfe/href ::contact) :title "Contact"} "Contact"]]]])

(defn display-date
  [date-string]
  (let [date (js/Date. date-string)]
    (. date (toLocaleDateString))))

(defn blog-post-preview
  [blog-post]
  [:div {:class '[border-2 rounded border-gray-500
                  m-8 pt-2 pb-4 px-4
                  bg-gray-200 dark:bg-gray-800]}
   [:div {:class '[flex]}
    [:a {:href  (rfe/href ::post {:id (-> blog-post :data :id)})
         :class '[]}
     [:h1 {:class '[text-2xl]} (:title (-> blog-post :data :title))]]]
   [:p {:class '[text-xs py-0.5 text-gray-500]}
    (display-date (-> blog-post :data :date))]
   [:p {:class '[text-sm overflow-ellipsis line-clamp-5
                 text-gray-600 dark:text-gray-400]}
    (:content blog-post)]])

(defn blog-post-main-view
  [blog-post]
  [:article {:class '[m-2 mt-6 pt-2 pb-4 px-2 sm:px-8]}
   [:div {:class '[prose prose-sm sm:prose lg:prose-lg xl:prose-2xl mx-auto]}
    [:h1 (-> blog-post :data :title)]
    [:p {:class '[py-2 text-gray-700]}
     "Written on "
     (display-date (:date (:data blog-post)))]
    [:article {:dangerouslySetInnerHTML {:__html (:content blog-post)}}]]])

(defn generic-link
  [link message & mail?]
  (let [link (cond (not (nil? mail?)) (str "mailto: " link)
                   :else              link)]
    [:a {:href   link
         :target "_blank"
         :class  '[text-blue-500 hover:text-blue-600
                   dark:text-blue-300 dark:hover:text-blue-200]} message]))

(defn license
  []
  [:footer {:class '[p-2 text-xs text-center self-center]}
   [:p "West's "
    (generic-link
     "https://github.com/wildwestrom/mysite" "static site generator")
    " is licensed"
    [:br {:class '[xs:hidden block]}]
    " under the "
    (generic-link
     "https://www.gnu.org/licenses/agpl-3.0.html" "GNU AGPL License")
    "." [:br]
    "The rest is my own original work"
    [:br {:class '[xs:hidden block]}]
    " unless otherwise specified."
    [:br]
    "Copyright © " (:year data) " " (:author data) " "
    [:br {:class '[xs:hidden block]}]
    (generic-link (:email data) (:email data) true)]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pages
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn home-page
  []
  [:div {:class '[flex flex-col flex-grow]}
   [:div {:class '[flex-grow flex justify-center items-center]}
    [:h2 {:class '[text-5xl p-4 italic]}
     "Welcome," [:br {:class '[sm:block hidden]}] " to my site!"]]])

(defn blog-preview-page
  []
  [:div {:class '[flex-grow]}
   (for [blog-post @blog-posts]
     ^{:key (-> blog-post :data :id)}
     [blog-post-preview (:content blog-post)])])

(defn blog-post-page
  []
  (let [id   (->> @match :parameters :path :id)
        post (first (filter #(= id (-> % :data :id)) @blog-posts))]
    [:div {:class '[]}
     (blog-post-main-view post)]))

(defn contact-page
  []
  [:div {:class '[flex-grow flex flex-col items-stretch]}
   [:div {:class '[flex-grow self-center flex flex-col]}
    [:div {:class '[p-4 flex flex-col flex-grow justify-center]}
     [:h2 {:class '[pb-4 text-2xl]} "You seem pretty sweet." [:br]
      "We should get lunch sometime."]
     [:ul
      [:li {:class '[p-2]}
       (generic-link (:email data) (:email data) true)]
      [:li {:class '[p-2]}
       (generic-link (:github data) (:github data))]]]]
   [license]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Routing
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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
  (rf/router routes {:data {:coercion rss/coercion}}))

(defn router-init!
  []
  (rfe/start!
   router
   (fn [m] (reset! match m))
    ;; set to false to enable HistoryAPI
   {:use-fragment true}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Main Page
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn app
  []
  [:div {:class '[text-gray-800 bg-gray-50
                  dark:text-gray-300 dark:bg-gray-700
                  subpixel-antialiased min-h-screen
                  flex flex-col]}
   [navbar]
   (if @match
     (let [view (:view (:data @match))]
       [view @match])
     (.log js/console
           (str "Match not found.\n `(:data @match)`:"
                (:data @match))))])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mount-root
  [component]
  (r.dom/render component (.getElementById js/document "app")))

(defn ^:dev/after-load start
  []
  (.log js/console "start")
  (mount-root [app]))

(defn ^:export init
  []
  (.log js/console "init")
  (router-init!)
  (start))

(defn ^:dev/before-load stop
  []
  (.log js/console "stop"))
