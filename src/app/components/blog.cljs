(ns app.components.blog
  (:require ["fitvids" :as fitvids]
            ["highlight.js" :as hljs]
            [reagent.dom :as r.dom]
            [reagent.core :as reagent]
            [app.components.common :as common]
            [app.data :as data]
            [reitit.frontend.easy :as rfe]))

;; State
(defonce blog-posts (reagent/atom nil))

(data/store-posts-data blog-posts)

;; Components
(defn- blog-post-preview
  [blog-post]
  [:div {:class ["border-2" "rounded" "border-gray-500" "m-2" "pt-2" "pb-4" "px-4" "bg-gray-200"]}
   [:a {:href (rfe/href :app.router/post {:id (-> blog-post :meta :id)})}
    [:h2 {:class ["text-2xl"]} (-> blog-post :meta :title)]
    [:p {:class ["text-xs py-0.5 text-gray-500"]}
     (common/display-date (-> blog-post :meta :date))]
    [:p {:class ["text-sm" "overflow-ellipsis" "line-clamp-5" "text-gray-600"]}
     (-> blog-post :meta :subtitle)]]])

(defn- highlight-code [node]
  (when (.querySelector (r.dom/dom-node node) "pre code")
    (-> (r.dom/dom-node node)
        (.querySelectorAll "pre code")
        array-seq
        first
        hljs/highlightElement)
    (doseq [code-block (array-seq (.querySelectorAll (r.dom/dom-node node) "pre code"))]
      (hljs/highlightElement code-block))))

(defn- blog-post-main-view
  [blog-post]
  (reagent/create-class
   {:component-did-mount
    (fn [node]
      (highlight-code node)
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
