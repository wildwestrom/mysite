#_(ns app.render-static
  (:require [app.components.nightwind :refer [inject-dark-mode]]
            [app.components.main :as pages]
            [app.router :refer [router-init!]]
            [reagent.dom :as r.dom]
            [reagent.dom.server :as r.serv]))

#_(r.serv/render-to-static-markup [pages/app])
