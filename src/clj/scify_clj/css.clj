(ns scify-clj.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
           [:body {
                   :font-family "sans-serif"
                   :max-width   "640px"
                   :margin      "0 auto"
                   :padding-top "72px"
                   }]
           [:.sentence {
                        :border        "1px solid rgb(230, 233, 239)"
                        :border-radius "0.25rem"
                        :padding       "1rem"
                        :margin-top    "1rem"
                        :margin-bottom "1rem"
                        }]
           [:.article-link {
                            :font-size       "1rem"
                            :text-decoration "underline"
                            }]
           [:.border {
                      :border-bottom "1px solid black"
                      :margin-top    "6rem"
                      :margin-bottom "6rem"
                      }]
           [:.button {
                      :background-color "#fff"
                      :border "1px solid #e6eaf1"
                      }]
           )
