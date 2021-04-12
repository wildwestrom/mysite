(ns app.core
  (:require [reagent.dom :as dom]))

(def body-classes
  '[dark:bg-gray-900 dark:text-gray-300])

(def navbar
  [:header {:class '[text-lg italic underline font-serif]}
   [:h1 "West's Super Awesome Homepage!"]])

(def greeting
  [:div {:id    "content"
         :class '[flex-1 mb-auto px-5 pr-18]}

   [:h2 {:class '[text-3xl py-2 italic]} "Welcome, to my site!"]
   [:p {:class '[font-mono]}"Pretty soon you're gonna see my blog and other goodies.
Trust me, you're gonna love it. A'ight? A'ight."]])

(def license
  [:footer {:class '[text-xs text-center]}
   [:p
    [:span.inline-block "My "
     [:a {:href   "https://gitlab.com/wildwestrom/cljs-tailwind-static"
          :target "_blank"
          :class  '[text-blue-500 visited:text-purple-500]}
      "static site generator"]
     " is licensed"] " "
    [:span.inline-block "under the "
     [:a {:href   "https://www.gnu.org/licenses/agpl-3.0.html"
          :target "_blank"
          :class  '[text-blue-500 visited:text-purple-500]} "GNU AGPL license"] "."] [:br]
    [:span.inline-block "The rest is my own original work"] " "
    [:span.inline-block "unless otherwise specified."] [:br]
    [:span.inline-block "Copyright © 2021 Christian Westrom "] " "
    [:span.inline-block "c.westrom@westrom.xyz"]]])

(defn app []
  [:div {:class '[flex flex-col
                  justify-between px-6 pt-4 pb-2]}
   [:div {:class '[h-screen]}
    navbar
    greeting]
   license])

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
