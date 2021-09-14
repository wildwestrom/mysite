(ns app.components.home)

(defn home-page
  []
  [:h2 {:class ["text-5xl" "p-4" "italic" "justify-self-center" "self-center"]}
   "Welcome," [:br {:class ["block" "sm:hidden"]}] " to my site!"])
