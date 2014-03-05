(ns datomic-explanation.demo-8
  (:require [datomic.api :as d]))

(def uri "datomic:mem://demo8")

(d/create-database uri)

(def connection (d/connect uri))
(def db-schema "resources/schema.edn")
(def schema (read-string (slurp db-schema)))

(d/transact connection schema)

(d/transact connection [{:db/id #db/id [:db.part/db -1]
                         :user/first-name "felix"
                         :user/last-name "flores"
                         :user/email "felix@neo.com"}])

(d/transact connection [[:db/add #db/id [:db.part/db -1] :user/first-name "romeo"]])

(def database (d/db connection))

(def query '[:find ?first-name ?tx ?add-retract
            :where
            [_ :user/first-name ?first-name ?tx ?add-retract]])

(d/q query (d/history database))
