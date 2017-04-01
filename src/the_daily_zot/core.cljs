(ns the-daily-zot.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defonce body-parser (nodejs/require "body-parser"))
(defonce express (nodejs/require "express"))
(defonce http (nodejs/require "http"))

;; app gets redefined on reload
(def app (-> (express)
             (.use (.urlencoded body-parser #js {:extended false}))
             (.use (.json body-parser))))

;; routes get redefined on each reload
(doto app
  (.get "/" #(.send %2 "hello, world"))
  (.post "/twilio" (fn [req res]
                     (.send res (.-body req)))))

(def -main
  (fn []
    ;; This is the secret sauce. you want to capture a reference to
    ;; the app function (don't use it directly) this allows it to be redefined on each reload
    ;; this allows you to change routes and have them hot loaded as you
    ;; code.
    (doto (.createServer http #(app %1 %2))
      (.listen (or (.-PORT (.-env js/process)) 3000)))))

(set! *main-cli-fn* -main)
