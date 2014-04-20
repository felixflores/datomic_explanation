(ns datomic-explanation.demo-3
  (:require [datomic.api :as d]))

(def uri "datomic:mem://a-database")
(d/create-database uri)
(def connection (d/connect uri))

(def a-database (d/db connection))
(d/touch (d/entity a-database :db.part/db))
