(ns app.core
  (:require [reagent.dom :as dom]))

(def body-classes
  '[dark:bg-gray-900 dark:text-gray-300])

(defn app []
  [:div {:class '[flex flex-col h-screen
                  justify-between p-6]}

   [:header {:class '[text-lg italic underline font-serif]}
    [:h1 "West's Super Awesome Homepage!"]]

   [:div {:id    "content"
          :class '[flex-1 mb-auto px-5 pr-18]}

    [:h2 {:class '[text-3xl py-2 italic]} "Welcome, to my site!"]
    [:p {:class '[font-mono]}"Pretty soon you're gonna see my blog and other goodies.
Trust me, you're gonna love it. A'ight? A'ight."]]

   [:footer {:class '[text-xs text-center p-2]}
    [:p "My "
     [:a {:href  "https://gitlab.com/wildwestrom/cljs-tailwind-static"
          :class '[text-blue-500 visited:text-purple-500]}
      "static site generator"]
     " is licensed under the "
     [:a {:href  "https://www.gnu.org/licenses/agpl-3.0.html"
          :class '[text-blue-500 visited:text-purple-500]} "AGPL license"] ". " [:br]
     "The rest is my own original work unless otherwise specified. " [:br]
     "Copyright © 2021 Christian Westrom c.westrom@westrom.xyz"]]])

(defn mount-root [c]
  (dom/render [c]
              (.getElementById js/document "app")))

(defn ^:dev/after-load start []
  (js/console.log "start")
  (mount-root app))

(defn ^:export init []
  (js/console.log "init")
  (doseq [class body-classes]
    (.classList.add
      (.getElementById js/document "body") class))
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
