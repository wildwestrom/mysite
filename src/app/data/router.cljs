(ns app.data.router
  (:require [app.data.global :as data]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [app.pages.about :refer [about-page]]
            [app.pages.blog :refer [blog-preview-page blog-post-page]]
            [app.pages.projects :refer [projects-page]]))

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
