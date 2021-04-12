(ns app.core
  (:require [reagent.dom :as dom]))

(def body-class
  "dark:bg-gray-900")

(defn app []
  [:div {:class '[dark:text-gray-300 flex flex-col
                  h-screen justify-between]}
   [:div {:id    "content"
          :class '[flex-1 text-left m-4 p-4 mb-auto]}
    [:h1 {:class '[text-5xl pb-2]} "Hello, World!"]
    [:h2 {:class '[text-3xl py-2]} "What do we have here?"]
    [:p "Looks like some fine styles here!"]
    [:p "Welcome to my site!"]]
   [:footer {:class '[text-xs text-center w-full p-2]}
    [:p "My "
     [:a {:href  "https://gitlab.com/wildwestrom/cljs-tailwind-static"
          :class '[text-blue-500 visited:text-purple-500]}
      "static site generator"]
     " is licensed under the "
     [:a {:href  "https://www.gnu.org/licenses/agpl-3.0.html"
          :class '[text-blue-500 visited:text-purple-500]} "AGPL license"] "."
     [:br] "The rest is my own original work unless otherwise specified."
     [:br] "Copyright © 2021 Christian Westrom c.westrom@westrom.xyz"]]])

(defn mount-root [c]
  (dom/render [c]
              (.getElementById js/document "app")))

(defn ^:dev/after-load start []
  (js/console.log "start")
  (mount-root app))

(defn ^:export init []
  (js/console.log "init")
  (.classList.add (.getElementById js/document "body") body-class)
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
