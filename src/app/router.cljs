(ns app.router
  (:require [app.components :as components]
            [app.data :as data]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::home
     :view components/home-page}]
   ["/blog"
    {:name ::blog
     :view components/blog-preview-page}]
   ["/blog/:id"
    {:name ::post
     :view components/blog-post-page
     :parameters {:path {:id string?}}}]
   ["/about"
    {:name ::about
     :view components/about-page}]])

(def router
  (rf/router routes))

(defn router-init!
  []
  (rfe/start! router
              (fn [m] (reset! data/match m))
    ;; set to false to enable HistoryAPI
    ;; set to true for development
              {:use-fragment false}))
