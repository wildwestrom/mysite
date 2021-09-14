(ns app.components.not-found)

(defn not-found-page
  []
  [:h2 {:class ["text-5xl" "p-4" "font-mono"]}
   "Error:" [:br] "Page not found."])
