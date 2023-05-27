const GetSum = (arr) => {
    var sum = 0;

   arr.map(item => {
     var temp = item.price * item.quantity;
    sum += temp;
   });
   return sum;
}

export default GetSum;