(ns core
  (:require [ring.adapter.jetty :refer [run-jetty]]))

(defn random-uuid []
  (java.util.UUID/randomUUID))

(def contacts (atom (let [id (random-uuid)]
                      {id {:id id
                           :full-name "Jony B Good"
                           :skills ["Clojure" "Data Science"]}})))


(defn handler "request-response"
  [req]
  (if (and (= (:request-method req) :get) (= (:uri req) "/"))
  {:status 200
   :headers {"Content-Type"  "application/edn; charset=UTF-8"}
   :body (prn-str (vals @contacts))}))

;;Here we utilize var type handler to mantain the handler actual
(defonce server
         (run-jetty #'handler {:port 3000
                               :join? false}))


