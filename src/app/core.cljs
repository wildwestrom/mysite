(ns app.core
  (:require [reagent.dom :as dom]
            [app.data :as data]))

(def root-classes '[bg-white dark:bg-black])

(def blog-post
  {:title "Lorem Ipsum"
   :body  "Yeah there's nothing much to see here."})

;; Views
(defn topbar [message title route]
  [:div {:class '[bg-black bg-opacity-50
                  p-2 text-lg font-serif
                  text-gray-50 dark:text-gray-300
                  sticky top-0]}
   [:a {:href  route
        :title title}
    [:h1 {:class '[underline italic p-2]}
     message]]])

(defn blog-post-small []
  [:div {:class '[border-2 rounded border-gray-500
                  m-8 mt-16 pt-2 pb-4 px-4
                  bg-gray-200 dark:bg-gray-800]}
   [:a {:href (str "/" (:title blog-post))}
    [:h1 {:class '[text-2xl]} (:title blog-post)]]
   [:p {:class '[overflow-ellipsis line-clamp-5 text-gray-600 dark:text-gray-400]}
    (:body blog-post)]])

(def blog-posts-overview
  [:div {:id    "blog"
         :class '[min-h-screen]}
   (topbar "Here's some cool blog posts you can check out." "Blog" "/blog")
   (blog-post-small)])

(def content
  [:div {:id    "content"
         :class '[min-h-screen flex flex-col]}
   (topbar "My Homepage!" "Home" "/")
   [:div {:class '[flex-grow flex justify-center items-center]}
    [:h2 {:class '[text-5xl p-4 italic]}
     "Welcome," [:br {:class '[sm:block hidden]}] " to my site!"]]])

(defn generic-link [link message & mail?]
  (let [link (cond (not (nil? mail?)) (str "mailto: " link)
                   :else              link)]
    [:a {:href   link
         :target "_blank"
         :class  '[text-blue-500 hover:text-blue-600
                   dark:text-blue-300 dark:hover:text-blue-200]} message]))

(def license
  [:footer {:class '[p-4 text-xs text-center self-center]}
   [:p "My "
    (generic-link "https://github.com/wildwestrom/mysite" "static site generator")
    " is licensed" [:br {:class '[xs:block hidden]}]  " under the "
    (generic-link "https://www.gnu.org/licenses/agpl-3.0.html" "GNU AGPL License")
    "." [:br]
    "Copyright © " data/year " " data/author " " [:br {:class '[xs:block hidden]}]
    (generic-link data/email data/email true)]])

(def contact
  [:div {:class '[min-h-screen flex flex-col items-stretch]}
   (topbar "Let's get in touch."
           "Contact" "/contact")
   [:div {:class '[flex-grow self-center flex flex-col]}
    [:div {:class '[p-4 flex flex-col flex-grow justify-center]}
     [:h2 {:class '[pb-4 text-2xl]} "You seem pretty sweet." [:br]
      "We should get lunch sometime."]
     [:ul
      [:li {:class '[p-2]}
       (generic-link data/email data/email true)]
      [:li {:class '[p-2]}
       (generic-link data/github data/github)]]]]
   license])

(defn app []
  [:div {:class '[text-gray-800 bg-gray-50
                  dark:text-gray-300 dark:bg-gray-700]}
   content
   blog-posts-overview
   contact])

;; Render
(defn mount-root [c]
(dom/render [c]
            (.getElementById js/document "app")))

(defn ^:dev/after-load start []
(js/console.log "start")
(mount-root app))

(defn ^:export init []
(js/console.log "init")
(doseq [class root-classes]
  (.classList.add
    (.getElementById js/document "root") class))
(start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
