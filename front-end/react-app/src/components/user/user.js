import React, { Component } from "react";
import "../../assets/styles/user-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";

import HistoryTable from "./historyTable";
import Nav from "../product/nav";
import UserDetails from "./userDetails";
import phoneImg from "../../assets/img/phone.png";
import spaghettiImg from "../../assets/img/spaghetti.png";
import microphoneImg from "../../assets/img/microphone.png";
import cartImg from "../../assets/img/Vector (2).png";
import historyImg from "../../assets/img/Vector (3).png";
import Footer from "./footer";
import CartTable from "./cartTable";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
// import { useModal } from 'react-hooks-use-modal';

import PopUp1 from "./popUp";
import axios from "../../api/axios";

class User extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "username",
      userInfo: {
        username: "",
        password: "",
        email: "",
        birthDate: 0,
        address: "",
        credit: 0,
        latestDiscountCode: "",
        buylist: [],
        comments: [],
        purchased: [],
        liked: [],
        disliked: [],
        userCoupons: [],
      },

      AccountDetailsEX: {
        userName: " Marshal",
        EMail: " Marshal.Mathers@gmail.com",
        userBirthday: " 1972/10/17",
        userAddress: " 20785 Schultes Avenue, Warren, MI 48091",
        budget: 10000000,
      },

      cartCommodities: [
        {
          commodityID: 313,
          img: phoneImg,
          productName: "Galexy S21",
          categories: ["Technology", "Phone"],
          price: 21000000,
          providerID: 1234,
          rating: 8.3,
          inStock: 17,
        },
        {
          commodityID: 1234,
          img: phoneImg,
          productName: "Galexy S21",
          categories: ["Technology", "Phone"],
          price: 21000000,
          providerID: 313,
          rating: 8.3,
          inStock: 17,
        },
      ],

      cartCommodities2: [],

      historyTableData: [
        {
          img: spaghettiImg,
          productName: "Mom's Spaghetti",
          categories: ["Food"],
          price: 60000,
          providerID: 313,
          rating: 10,
          inStock: 0,
          quantity: 3,
        },
        {
          img: microphoneImg,
          productName: "Dre's Microphone",
          categories: ["Technology"],
          price: 4200000,
          providerID: 4321,
          rating: 8.5,
          inStock: 22,
          quantity: 1,
        },
      ],

      cartCommoditiesCount2: [
        {
          commodityID: 1,
          cartItemsCount: 5,
        },
      ],

      cartCommoditiesCount: [
        {
          commodityID: 1234,
          cartItemsCount: 1,
        },
        {
          commodityID: 313,
          cartItemsCount: 1,
        },
      ],
    };
    this.updateCartCommoditiesCount =
      this.updateCartCommoditiesCount.bind(this);
  }

  getUserData() {
    axios
      .get("/user")
      .then((resp) => {
        console.log(resp.status);
        if (resp.status === 200) {
          let buylistCommoditiesId = [];
          let commentsId = [];
          let purchasedlistCommoditiesId = [];
          let likedCommentsId = [];
          let dislikedCommentsId = [];
          let usedCouponsId = [];
          let userBuyList = [];
          console.log(resp.data.userInfo);
          console.log(resp.data.cartCommodities);

          Object.keys(resp.data.cartCommodities).forEach((item) => {
            userBuyList.push(resp.data.cartCommodities[item]);
          });

          Object.keys(resp.data.userInfo.buyList).forEach((item) => {
            buylistCommoditiesId.push(resp.data.userInfo.buyList[item]);
          });

          Object.keys(resp.data.userInfo.commentsList).forEach((item) => {
            commentsId.push(resp.data.userInfo.commentsList[item]);
          });

          Object.keys(resp.data.userInfo.purchasedList).forEach((item) => {
            purchasedlistCommoditiesId.push(
              resp.data.userInfo.purchasedList[item]
            );
          });

          Object.keys(resp.data.userInfo.likedComments).forEach((item) => {
            likedCommentsId.push(resp.data.userInfo.likedComments[item]);
          });

          Object.keys(resp.data.userInfo.dislikedComments).forEach((item) => {
            dislikedCommentsId.push(resp.data.userInfo.dislikedComments[item]);
          });

          Object.keys(resp.data.userInfo.usedDiscountCoupons).forEach(
            (item) => {
              usedCouponsId.push(resp.data.userInfo.usedDiscountCoupons[item]);
            }
          );

          this.setState(
            {
              userInfo: {
                username: resp.data.userInfo.username,
                password: resp.data.userInfo.password,
                email: resp.data.userInfo.email,
                birthDate: resp.data.userInfo.birthDate,
                address: resp.data.userInfo.address,
                credit: resp.data.userInfo.credit,
                latestDiscountCode:
                  resp.data.userInfo.latestSelectedDiscountCode,
                buylist: buylistCommoditiesId,
                comments: commentsId,
                purchased: purchasedlistCommoditiesId,
                liked: likedCommentsId,
                disliked: dislikedCommentsId,
                userCoupons: usedCouponsId,
              },
              cartCommodities2: userBuyList,
            },
            () => {
              console.log(this.state.userInfo);
              console.log(this.state.cartCommodities2);
            }
          );
        }
      })
      .catch((error) => {
        if (error.response.status === 401) {
          window.location.href = "http://localhost:3000/login";
        }
      });
  }

  componentDidMount() {
    this.getUserData();
    toast.configure({
      rtl: true,
      className: "text-center",
      position: toast.POSITION.TOP_CENTER,
      autoClose: 5000,
      closeOnClick: true,
    });
  }

  updateCartCommoditiesCount(commodityID, count) {
    this.state.cartCommoditiesCount2.forEach((item) => {
      if (item.commodityID == commodityID) {
        item.cartItemsCount = count;
        console.log(item.cartItemsCount);
      }
    });

    // console.log(this.state.cartCommoditiesCount[0].cartItemsCount);
    // this.state.cartCommoditiesCount.map(item => {
    //   if(item.commodityID === commodityID){
    //     return item.cartItemsCount;
    //   }
    // })
  }

  addCreditToUser = (event, value) => {
    event.preventDefault();
    axios
      .post("/user/addCredit", {
        credit: value,
      })
      .then((resp) => {
        if (resp.status === 200) {
          this.setState((prevState) => {
            let userInfo = { ...prevState.userInfo };
            userInfo.credit = resp.data;
            return { userInfo };
          });
        }
      })
      .catch((error) => {
        if (error.response.status === 401) {
          window.location.href = "http://localhost:3000/login";
        } else if (error.response.status === 403) {
          toast.error("Please enter a valid value");
        } else if (error.response.status === 400) {
          window.location.href = "http://localhost:3000/badrequest";
        }
      });
  };

  render() {
    return (
      <div>
        <Nav
          username={this.state.userInfo.username}
          cartItemsCount={this.state.cartCommodities.length}
        />
        <div class="container body-container">
          <UserDetails
            onAddCredit={this.addCreditToUser}
            AccountDetails={this.state.userInfo}
          />
          <h3 class="text-brown">
            <img src={cartImg} class="cart-img" alt="" /> Cart
          </h3>
          <CartTable
            cartCommodities={this.state.cartCommodities2}
            updateItemCount={this.updateCartCommoditiesCount}
          />
          <div class="row">
            <PopUp1
              cartCommodities={this.state.cartCommodities2}
              cartCommoditiesCount={this.state.cartCommoditiesCount2}
            />
          </div>

          <h3 class="text-brown mt-20">
            <img src={historyImg} class="cart-img" alt="" /> History
          </h3>
          <HistoryTable historyTableData={this.state.historyTableData} />
        </div>

        <Footer />
      </div>
    );
  }
}

export default User;
