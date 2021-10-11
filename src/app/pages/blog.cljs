(ns app.pages.blog
  (:require ["fitvids" :as fitvids]
            [app.components.common :as common]
            [app.data.global :as data]
            [clojure.edn :as edn]
            [kitchen-async.promise :as p]
            [lambdaisland.fetch :as fetch]
            [reagent.core :as reagent]
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
  [:div.blog-post-preview
   [:a {:href (rfe/href :app.data.router/post {:id (-> blog-post :meta :id)})}
    [:h3 (-> blog-post :meta :title)]
    [:time (common/display-date (-> blog-post :meta :date))]
    [:p (-> blog-post :meta :subtitle)]]])

(defn- blog-post-main-view
  [blog-post]
  (reagent/create-class
   {:component-did-mount
    (fn [_]
      #_(highlight-code node)
      (fitvids))
    :reagent-render
    (fn [blog-post]
      [:<>
       [:h1.mb-2 (-> blog-post :meta :title)]
       [:time
        (common/display-date (-> blog-post :meta :date))]
       [:article {:class ["self-center" "prose" "prose-blue" "sm:prose-sm" "mt-4"]
                  :dangerouslySetInnerHTML
                  {:__html (:content blog-post)}}]])}))

(defn blog-preview-page
  []
  [:<>
   [:h2 "My thoughts"]
   (for [blog-post @blog-posts]
     ^{:key (-> blog-post :meta :id)}
     [blog-post-preview blog-post])])

(defn blog-post-page
  []
  (let [id   (-> @data/current-page :parameters :path :id)
        post (first (filter #(= id (-> % :meta :id)) @blog-posts))]
    [blog-post-main-view post]))
