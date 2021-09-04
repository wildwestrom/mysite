(ns app.core
  (:require [app.components :as components]
            [app.data :as data]
            [app.nightwind :refer [inject-dark-mode]]
            [reagent.dom :as r.dom]
            [reitit.frontend.easy :as rfe]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn router-init!
  []
  (rfe/start!
    components/router
    (fn [m] (reset! data/match m))
    ;; set to false to enable HistoryAPI
    ;; Never-mind this makes a huge fucking bug
    {:use-fragment true}))

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
