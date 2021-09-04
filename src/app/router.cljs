(ns app.router
  (:require [app.components
             :refer
             [blog-post-page blog-preview-page contact-page home-page]]
            [app.data :as data]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Router
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def routes
  [["/"
    {:name ::home
     :view home-page}]

   ["/blog"
    {:name ::blog
     :view blog-preview-page}]

   ["/blog/:id"
    {:name       ::post
     :view       blog-post-page
     :parameters {:path {:id string?}}}]

   ["/contact"
    {:name ::contact
     :view contact-page}]])

(def router
  (rf/router routes))

(defn router-init!
  []
  (rfe/start!
   router
   (fn [m] (reset! data/match m))
    ;; set to false to enable HistoryAPI
    ;; Never-mind it doesn't work for some reason
   {:use-fragment true}))
