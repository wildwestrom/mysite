(ns app.data.router
  (:require [app.data.global :as data]
            [app.pages.about :refer [about-page]]
            [app.pages.blog :refer [blog-post-page blog-preview-page]]
            [app.pages.projects :refer [projects-page]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::about
     :view about-page}]
   ["/projects"
    {:name ::projects
     :view projects-page}]
   ["/blog"
    {:name ::blog
     :view blog-preview-page}]
   ["/blog/:id"
    {:name ::post
     :view blog-post-page
     :parameters {:path {:id string?}}}]])

(def router
  (rf/router routes))

(defn router-init!
  []
  (rfe/start! router
              (fn [m] (reset! data/match m))
              {:use-fragment false}))
