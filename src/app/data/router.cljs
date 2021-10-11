(ns app.data.router
  (:require [app.data.global :as data]
            [app.pages.home :refer [home-page]]
            [app.pages.blog :refer [blog-post-page blog-preview-page]]
            [app.pages.projects :refer [projects-page]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::home
     :display "Who am I?"
     :view home-page}]
   ["/projects"
    {:name ::projects
     :display "My portfolio"
     :view projects-page}]
   ["/blog"
    {:name ::blog
     :display "My thoughts"
     :view blog-preview-page}]
   ["/blog/:id"
    {:name ::post
     :display "Blog post"
     :view blog-post-page
     :parameters {:path {:id string?}}}]])

(def router
  (rf/router routes))

(defn router-init!
  []
  (rfe/start! router
              (fn [m] (reset! data/current-page m))
              {:use-fragment false}))
