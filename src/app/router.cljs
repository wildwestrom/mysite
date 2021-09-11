(ns app.router
  (:require [app.pages :as pages]
            [app.data :as data]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::home
     :view pages/home-page}]
   ["/projects"
    {:name ::projects
     :view pages/projects-page}]
   ["/blog"
    {:name ::blog
     :view pages/blog-preview-page}]
   ["/blog/:id"
    {:name ::post
     :view pages/blog-post-page
     :parameters {:path {:id string?}}}]
   ["/about"
    {:name ::about
     :view pages/about-page}]])

(def router
  (rf/router routes))

(defn router-init!
  []
  (rfe/start! router
              (fn [m] (reset! data/match m))
    ;; set to false to enable HistoryAPI
    ;; set to true for development
              {:use-fragment false}))
