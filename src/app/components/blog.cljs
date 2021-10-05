(ns app.components.blog
  (:require ["fitvids" :as fitvids]
            [app.components.common :as common]
            [app.data :as data]
            [clojure.edn :as edn]
            [clojure.string :as string]
            [kitchen-async.promise :as p]
            [lambdaisland.fetch :as fetch]
            [reagent.core :as reagent]
            [reagent.dom :as r.dom]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; State
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce blog-posts (reagent/atom nil))

(def posts-route "/posts/")

(def all-posts-uri (str posts-route "_ALL_POSTS.edn"))

(defn store-posts-data
  [a]
  (let [extract-body (fn [res] (edn/read-string (:body res)))]
    (p/let [filenames  (fetch/get all-posts-uri)
            files      (extract-body filenames)
            files-resp (p/all (map #(fetch/get (str posts-route %)) files))]
      (reset! a (reverse (map extract-body files-resp)))
      (js/console.debug "Received blog-posts"))))

(store-posts-data blog-posts)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Components
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- blog-post-preview
  [blog-post]
  [:div {:class ["border-2" "rounded" "border-gray-500" "m-2" "pt-2" "pb-4" "px-4" "bg-gray-200"]}
   [:a {:href (rfe/href :app.router/post {:id (-> blog-post :meta :id)})}
    [:h2 {:class ["text-2xl"]} (-> blog-post :meta :title)]
    [:p {:class ["text-xs py-0.5 text-gray-500"]}
     (common/display-date (-> blog-post :meta :date))]
    [:p {:class ["text-sm" "overflow-ellipsis" "line-clamp-5" "text-gray-600"]}
     (-> blog-post :meta :subtitle)]]])

#_(defn- highlight-code [node]
  (when-let [blocks (array-seq (.querySelectorAll (r.dom/dom-node node) "pre code"))]
    ;;TODO Try making a system that allows me to detect what code blocks there are, as well as download only the languages I need on-the-fly
    (let [languages (set (map #(string/replace (str (.-classList %)) "language-" "") blocks))]
      (run! (fn [lang]
              (js/console.log lang)
              (hljs/registerLanguage lang (js/require (str "highlight.js/lib/languages/" lang)))
              ) languages))
    (run! hljs/highlightElement blocks)))

(defn- blog-post-main-view
  [blog-post]
  (reagent/create-class
   {:component-did-mount
    (fn [node]
      #_(highlight-code node)
      (fitvids))
    :reagent-render
    (fn [blog-post]
      [:div {:class ["m-2" "mt-6" "pt-2" "z-10" "pb-4" "px-2" "sm:px-8" "overflow-auto grid"]}
       [:h1 {:class ["font-bold text-4xl"]} (-> blog-post :meta :title)]
       [:p {:class ["pb-4" "text-gray-500"]}
        (common/display-date (-> blog-post :meta :date))]
       [:article {:class ["prose" "prose-sm" "sm:prose" "lg:prose-lg" "justify-self-center"]
                  :dangerouslySetInnerHTML
                  {:__html (:content blog-post)}}]])}))

(defn- blog-preview-page
  []
  [:div {:class ["max-w-prose" "pt-2" "items-stretch" "self-start" "justify-self-center"]}
   (for [blog-post @blog-posts]
     ^{:key (-> blog-post :meta :id)}
     [blog-post-preview blog-post])])

(defn blog-post-page
  []
  (let [id   (->> @data/match :parameters :path :id)
        post (first (filter #(= id (-> % :meta :id)) @blog-posts))]
    [blog-post-main-view post]))
