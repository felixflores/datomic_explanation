(ns datomic-explanation.demo-6
  (:require [datomic.api :as d]))

(def uri "datomic:mem://demo6")

(d/create-database uri)

(def connection (d/connect uri))
(def db-schema "resources/schema.edn")
(def schema (read-string (slurp db-schema)))

(d/transact connection schema)

(d/transact connection [{:db/id #db/id [:db.part/db]
                         :user/first-name "felix"
                         :user/last-name "flores"
                         :user/email "felix@neo.com"}])

(d/transact connection [{:db/id #db/id [:db.part/db]
                         :user/email "felixflores@gmail.com"}])

(def database (d/db connection))

(d/q '[:find ?e
       :where
       [?e :user/first-name "felix"]
       [?e :user/last-name "flores"]]
     database)

(d/touch (d/entity database 66))

