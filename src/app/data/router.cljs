(ns app.data.router
  (:require [app.data.global :as data]
            [app.pages.home :refer [home-page]]
            [app.pages.blog :refer [blog-post-page blog-preview-page]]
            [app.pages.portfolio :refer [portfolio-page]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::home
     :display "My homepage"
     :view home-page}]
   ["/portfolio"
    {:name ::portfolio
     :display "My portfolio"
     :view portfolio-page}]
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
