use rent_and_use

db.users.insert(
    {
        firstName: 'Роман',
        lastName: 'Сюняков',
        city: 'Казань',
        age: 20,
        role: ['holder', 'renter'],
        offers: [ObjectId('5faff5be02eff33a9aed6da4')],
        registrationDate: '14.11.2020'
    }
    )

db.offers.insert(
    {
        title: 'Шуруповерт',
        brand: 'Makita',
        model: 'DF333DWYE',
        type: 'Строительные инструменты',
        city: 'Казань',
        district: 'Вахитовский',
        pricePerHouse: 100,
        pricePerDay: 600,
        pricePerMonth: 3500,
        description: 'Сдаю в аренду шуруповерт Makita DF333DWYE, пользовался пару раз, в отличном состоянии',
        phone: '79991549589',
        date: '14.11.2020',
        views: 100
    }
    );

db.offers.update({_id: ObjectId('5faff5be02eff33a9aed6da4')}, {
    $set: {
        user: ObjectId('5faff62e02eff33a9aed6da8')
    }
}
    );

db.users.find()
db.offers.find()