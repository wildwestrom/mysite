(ns app.core
  (:require [app.components :as components]
            [app.data :as data]
            [app.nightwind :refer [inject-dark-mode]]
            [app.router :refer [router-init!]]
            [reagent.dom :as r.dom]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mount-root
  [component]
  (r.dom/render component (.getElementById js/document "app")))

(defn ^:dev/after-load start
  []
  (.log js/console "start")
  (mount-root [components/app]))

(defn ^:export init
  []
  (.log js/console "init")
  (inject-dark-mode)
  (router-init!)
  (start))

(defn ^:dev/before-load stop
  []
  (.log js/console "stop"))
