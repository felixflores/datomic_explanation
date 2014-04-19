(ns datomic-explanation.demo-1
  (:require [datomic.api :as d]))

(def uri "datomic:mem://my-database")

; Create an in memory Datomic database
(d/create-database uri)
(def connection (d/connect uri))

; Establish a connection
(def database (d/db connection))

; Attempt to transact the following two datoms
(d/transact connection [[:db/add #db/id [:db.part/user -1] :db/ident "John Doe"]
                        [:db/add #db/id [:db.part/user -1] :email "doe@example.com"]])








