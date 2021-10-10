(ns app.portfolio
  (:require [app.components.common :as common]))

(def portfolio
  [{:group-name "Complete Projects"
    :projects   [{:name  "This Website"
                  :desc  [:p "This project started as an experiment in repl-driven-development, and is now the epicenter of my online presence. It's been really fun working with Reagent and learning more about how to use components from React."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/mysite"}]}
                 {:name  "Uniorg Util"
                  :desc  [:p "This is a supplemental project meant to help out with the blog portion of my site. It converts org documents to HTML and metadata for sorting and such. It's available as an NPM package. This is probably the best example of continuous integration and unit testing out of all my projects."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/uniorg-util"}
                          {:title "NPM Link"
                           :href  "https://www.npmjs.com/package/uniorg-util"}]}
                 {:name  "Hexagram SVG Generator"
                  :desc  [:p "The I-Ching is an ancient Chinese system of divination. It involves creating a figure of 6 lines, broken or unbroken, usually by casting coins or picking yarrow stalks. This demo generates an SVG graphic based on the hexagram number."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/hexagram-svg-generator"}
                          {:title "Demo"
                           :href  "https://hexagram-svg.netlify.app/"}]}
                 {:name  "TSDL"
                  :desc  [:p "There's this artist who makes some cool desktop wallpapers, so I decided to make a web-scraping tool to help me download all the images on her site. The earliest iteration of it was written in POSIX-compliant shell-script. Now it's has a swing-based user interface."]
                  :links [{:title "Source"
                           :href  "https://github.com/wildwestrom/tsdl"}
                          {:title "Release"
                           :href  "https://github.com/wildwestrom/tsdl/releases"}]}]}
   {:group-name "Unfinished Projects"
    :projects   [{:name  "Death Calendar"
                  :desc  [:p "I was inspired by a " [common/generic-link "https://youtu.be/JXeJANDKwDc" "Kurzgesagt video"] " (which was also inspired by a blog-post on " [common/generic-link "https://waitbutwhy.com/2014/05/life-weeks.html" "waitbutwhy.com"] ") to make a calendar that shows how long you have left to live visually. I figured I could make it into a desktop background so I can use it as a constant reminder of my ultimate demise, and hopefully spend more time on the things that really matter."]
                  :links [{:title "Source"
                           :link  "https://github.com/wildwestrom/death-calendar"}]}]}])
