const Match = (cartCommodities, cartCommoditiesCount) => {
    // cartCommoditiesCount.map( item2 => {
    //     if(item2.commodityID === item.commodityID) {
    //         return item2.cartItemsCount;
    //     }
    // })
    var arr = [];
    cartCommodities.map( item => {
        cartCommoditiesCount.map( item2 => {
            if(item2.commodityID === item.commodityID) {
                arr.push({
                    price: item.price,
                    name: item.productName,
                    quantity: item2.cartItemsCount
                })
            }
        })
    })

    return arr;
}

export default Match;