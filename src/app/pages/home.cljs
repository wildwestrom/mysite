(ns app.pages.home
  (:require ["@fortawesome/free-brands-svg-icons/faDiscord" :refer [faDiscord]]
            ["@fortawesome/free-brands-svg-icons/faGithub" :refer [faGithub]]
            ["@fortawesome/free-brands-svg-icons/faLinkedin" :refer [faLinkedin]]
            ["@fortawesome/free-brands-svg-icons/faMonero" :refer [faMonero]]
            ["@fortawesome/free-solid-svg-icons/faEnvelope" :refer [faEnvelope]]
            ["@fortawesome/free-solid-svg-icons/faKey" :refer [faKey]]
            [app.components.common :as common]
            [app.data.global :refer [global-config]]
            [reitit.frontend.easy :as rfe]))

(defn- section-header
  [title subtitle]
  [:<>
   [:h2 title]
   [:h3.italic.mb-2 subtitle]])

(defn home-page
  []
  [:<>
   [section-header "About"
    "My name is Christian Westrom, but everyone calls me West."]
   [:p "Over this past year I taught myself Clojure and ClojureScript, and I'd like to start working as a developer full-time. "]
   [section-header "Contact"
    [:<>
     [:p "You can check out my " [common/generic-link (rfe/href :app.data.router/projects) "portfolio" :alt-target "_self"] "."]
     [:p  "If you like what you see, please don't hesitate to send me a message."]]]
   [:ul
    [common/icon-link (:email global-config)
     faEnvelope
     "Email address"
     :href (str "mailto:" (:email global-config))]
    [common/icon-link "wildwestrom"
     faGithub
     "Github"
     :href (:github global-config)]
    [common/icon-link "c-westrom"
     faLinkedin
     "Linkedin"
     :href (:linkedin global-config)]
    [common/icon-link (:discord global-config)
     faDiscord
     "Discord"
     :copyable true]
    [common/icon-link "Public PGP Key"
     faKey
     "Public PGP Key"
     :href (:pgp global-config)
     :styles "mt-4"]]
   [section-header "Fund"
    "Coding is hard work; This helps me keep going."]
   [:ul
    [common/icon-link "Monero Wallet"
     faMonero
     "Monero wallet"
     :href (str "monero:" (:monero global-config))]]])
