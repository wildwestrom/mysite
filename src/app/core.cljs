(ns app.core
  (:require [clojure.string]
            [reagent.core :as reagent]
            [reagent.dom :as r.dom]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.coercion.spec :as rss]
            [app.data :as data]
            [app.components :as component]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn router-init!
  []
  (rfe/start!
    component/router
    (fn [m] (reset! data/match m))
    ;; set to false to enable HistoryAPI
    {:use-fragment true}))

(defn mount-root
  [component]
  (r.dom/render component (.getElementById js/document "app")))

(defn ^:dev/after-load start
  []
  (.log js/console "start")
  (mount-root [component/app])
  (component/inject-dark-mode))

(defn ^:export init
  []
  (.log js/console "init")
  (router-init!)
  (start))

(defn ^:dev/before-load stop
  []
  (.log js/console "stop"))
