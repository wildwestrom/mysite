(ns app.core
  (:require [app.pages :as pages]
            [app.components.nightwind :refer [inject-dark-mode]]
            [app.router :refer [router-init!]]
            [reagent.dom :as r.dom]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mount-root
  [component]
  (r.dom/render component (.getElementById js/document "app")))

(defn ^:export init
  []
  (js/console.debug "init")
  (inject-dark-mode)
  (router-init!)
  (mount-root [pages/app]))

(defn ^:dev/before-load stop
  []
  (js/console.debug "stop"))
