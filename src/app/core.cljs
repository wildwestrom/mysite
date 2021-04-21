(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [app.data :refer [data]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.coercion.spec :as rss]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Data
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def root-classes '[bg-white dark:bg-black])

(def blog-post
  {:title "Lorem Ipsum",
   :date  "2021 4 20",
   :body  "Yeah there's nothing much to see here. Sooner or later I'll get around to implementing my blog. It'll be pretty sweet. It's gonna parse org-mode documents and give a little preview in this here box. Trust me, you're gonna love it.
Penishole. Yes, I said penishole. Did I stutter? Here, I'll say it again. PENISHOLE!"})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Components
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn navbar []
  [:nav {:class '[bg-black bg-opacity-50
                  p-2 text-lg font-serif
                  text-gray-50 dark:text-gray-300
                  grid grid-flow-col grid-cols-2
                  underline italic]}
   [:div {:class '[p-2]}
    [:a {:href (rfe/href ::homepage) :title "Home"}
     [:h1 {:class '[]} "Home"]]]
   [:div {:class '[p-2 justify-end inline-flex]}
    [:div {:class '[px-2]}
     [:a {:href (rfe/href ::blog) :title "Blog"} "Blog"]]
    [:div {:class '[px-2]}
     [:a {:href (rfe/href ::contact) :title "Contact"} "Contact"]]
    ]])

(defn blog-post-preview []
  [:div {:class '[border-2 rounded border-gray-500
                  m-8 pt-2 pb-4 px-4
                  bg-gray-200 dark:bg-gray-800]}
   [:div {:class '[flex]}
    [:a {:href  (rfe/href ::post {:id (clojure.string/replace (:title blog-post) #"\s" "-")})
         :class '[]}
     [:h1 {:class '[text-2xl]} (:title blog-post)]]]
   [:p {:class '[text-xs py-0.5 text-gray-500]}
    (let [date (js/Date. (:date blog-post))]
      (. date (toLocaleDateString)))]
   [:p {:class '[text-sm overflow-ellipsis line-clamp-5
                 text-gray-600 dark:text-gray-400]}
    (:body blog-post)]])

(defn blog-post-main-view [blog-post]
  [:article {:class '[m-2 mt-6 pt-2 pb-4 px-2 sm:px-8]}
   [:div {:class '[prose prose-sm sm:prose lg:prose-lg xl:prose-2xl mx-auto]}
    [:h1 (:title blog-post)]

    [:p {:class '[py-2 text-gray-700]}
     "Written on "
     (let [date (js/Date. (:date blog-post))]
       (. date (toLocaleDateString)))]

    [:p {:class '[align-self-end px-2 mt-10]}
     (:body blog-post)]]])

(defn generic-link [link message & mail?]
  (let [link (cond (not (nil? mail?)) (str "mailto: " link)
                   :else              link)]
    [:a {:href   link
         :target "_blank"
         :class  '[text-blue-500 hover:text-blue-600
                   dark:text-blue-300 dark:hover:text-blue-200]} message]))

(defn license []
  [:footer {:class '[p-2 text-xs text-center self-center]}
   [:p "My "
    (generic-link
      "https://gitlab.com/wildwestrom/mysite" "static site generator")
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
    (generic-link data/email data/email true)]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Pages
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn home-page []
  [:div {:class '[min-h-screen flex flex-col]}
   [navbar]
   [:div {:class '[flex-grow flex justify-center items-center]}
    [:h2 {:class '[text-5xl p-4 italic]}
     "Welcome," [:br {:class '[sm:block hidden]}] " to my site!"]]])

(defn blog-preview-page []
  [:div {:class '[min-h-screen]}
   [navbar]
   (blog-post-preview)])

(defn blog-post-page []
  [:div {:class '[min-h-screen]}
   [navbar]
   (blog-post-main-view blog-post)
   ])

(defn contact-page []
  [:div {:class '[min-h-screen flex flex-col items-stretch]}
   [navbar]
   [:div {:class '[flex-grow self-center flex flex-col]}
    [:div {:class '[p-4 flex flex-col flex-grow justify-center]}
     [:h2 {:class '[pb-4 text-2xl]} "You seem pretty sweet." [:br]
      "We should get lunch sometime."]
     [:ul
      [:li {:class '[p-2]}
       (generic-link data/email data/email true)]
      [:li {:class '[p-2]}
       (generic-link data/gitlab data/gitlab)]]]]
   [license]])

;; Routing

(def routes
  [["/"
    {:name ::homepage
     :view home-page}]

   ["/blog"
    {:name ::blog
     :view blog-preview-page}]

   ["/blog/:id"
    {:name ::post
     :view blog-post-page
     ;; :parameters {:path  {:id int?}
     ;;              :query {(ds/opt :foo) keyword?}}
     }]

   ["/contact"
    {:name ::contact
     :view contact-page}]
   ])

(defonce match (r/atom nil))

(defn router-init! []
  (rfe/start!
    (rf/router routes {:data {:coercion rss/coercion}})
    (fn [m] (reset! match m))
    ;; set to false to enable HistoryAPI
    {:use-fragment true}))

;; Main Page

(defn app []
  (let [state (r/atom 0)]
    [:div {:class '[text-gray-800 bg-gray-50
                    dark:text-gray-300 dark:bg-gray-700
                    subpixel-antialiased]}
     (if @match
       (let [view (:view (:data @match))]
         [view @match]))]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn inject-styles [classes]
  (doseq [class classes]
    (.classList.add
      (.getElementById js/document "root") class)))

(defn mount-root [c]
  (dom/render [c]
              (.getElementById js/document "app")))

(defn ^:dev/after-load start []
(js/console.log "start")
(mount-root app))

(defn ^:export init []
  (js/console.log "init")
  (router-init!)
  (inject-styles root-classes)
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
