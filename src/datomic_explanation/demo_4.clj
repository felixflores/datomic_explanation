(ns datomic-explanation.demo-4
  (:require [datomic.api :as d]))

(def uri "datomic:mem://a-database")
(d/create-database uri)
(def connection (d/connect uri))

(def a-database (d/db connection))
(:db.install/attribute (d/entity a-database :db.part/db))

;=> #{:db/noHistory :db.excise/before :db.excise/beforeT :db.install/partition :db/cardinality :db.install/attribute :db/ind ex :db/excise :db/unique :db/fulltext :db.alter/attribute :db/txInstant :db/lang :db/doc :db.install/valueType :db.excis e/attrs :db/code :db/isComponent :db/fn :db.install/function :db/valueType :db/ident :fressian/tag}
