(ns app.router
  (:require [app.data :as data]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [app.components.about :refer [about-page]]
            [app.components.home :refer [home-page]]
            [app.components.blog :refer [blog-preview-page blog-post-page]]
            [app.components.projects :refer [projects-page]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::home
     :view home-page}]
   ["/projects"
    {:name ::projects
     :view projects-page}]
   ["/blog"
    {:name ::blog
     :view blog-preview-page}]
   ["/blog/:id"
    {:name ::post
     :view blog-post-page
     :parameters {:path {:id string?}}}]
   ["/about"
    {:name ::about
     :view about-page}]])

(def router
  (rf/router routes))

(defn router-init!
  []
  (rfe/start! router
              (fn [m] (reset! data/match m))
    ;; set to false to enable HistoryAPI
    ;; set to true for development
              {:use-fragment false}))
