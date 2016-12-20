class CreatePayments < ActiveRecord::Migration[5.0]
  def change
    create_table :payments do |t|
      t.references :order, foreign_key: true
      t.decimal :amount

      t.timestamps
    end
  end
end
