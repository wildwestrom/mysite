(ns app.render
  (:require [app.components.app :refer [app]]
            [app.components.nightwind :refer [inject-dark-mode]]
            [app.data.router :refer [router-init!]]
            [reagent.dom :as r.dom]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Render
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mount-root
  [component]
  (r.dom/render component (.getElementById js/document "app")))

(defn ^:export init
  []
  (js/console.log "init")
  (inject-dark-mode)
  (router-init!)
  (mount-root [app]))

(defn ^:dev/before-load stop
  []
  (js/console.log "stop"))
