(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]))

(def body-class
  "dark:bg-gray-700")

(defn app []
  [:div {:class '[m-6 p-6 dark:text-gray-200]}
   [:h1 {:class '[text-5xl pb-2]} "Hello, World!"]
   [:h2 {:class '[text-3xl py-2]} "What do we have here?"]
   [:p "Looks like some fine styles here!"]
   [:p "Welcome to my site!"]
   ])

(defn mount-root [c]
  (dom/render [c]
              (.getElementById js/document "app")
              ))

(defn ^:dev/after-load start []
  (js/console.log "start")
  (mount-root app))

(defn ^:export init []
  (js/console.log "init")
  (.classList.add (.getElementById js/document "body") body-class)
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
