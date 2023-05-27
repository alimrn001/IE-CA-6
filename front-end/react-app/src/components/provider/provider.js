import React, { Component } from "react";
import Nav from "../product/nav";
import Footer from "../product/footer";
import img from "../../assets/img/phone.png";
import huaweiImg from "../../assets/img/image 5.png";
import "../../assets/styles/provider-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Item from "./item";
import ProviderDetails from "./providerDetail";
import axios from "../../api/axios";

class Provider extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "username",
      cartItemsCount: 1,
      providerInfo: {
        providerId: 0,
        providerName: "",
        providerRegistryDate: 0,
        providerCommoditiesNum: 0,
        providerAvgCommoditiesRate: 0,
        commoditiesProvidedID: [],
        providerImage: "",
      },
      providedCommodities: [],
      // itemsEx: [
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 1,
      //     image: img,
      //   },
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 1,
      //     image: img,
      //   },
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 1,
      //     image: img,
      //   },
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 1,
      //     image: img,
      //   },
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 1,
      //     image: img,
      //   },
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 1,
      //     image: img,
      //   },
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 1,
      //     image: img,
      //   },
      //   {
      //     id: 1,
      //     name: "Huawei nova 9",
      //     price: 300,
      //     inStock: 0,
      //     image: img,
      //   },
      // ],
    };
  }

  getBalootProvider() {
    axios
      .get(`providers/${this.props.match.params.providerId}`)
      .then((resp) => {
        if (resp.status === 200) {
          let providedCommoditiesId = [];
          let providedCommoditiesData = [];
          Object.keys(resp.data.info.commoditiesProvided).forEach(
            (commodityId) => {
              providedCommoditiesId.push(
                resp.data.info.commoditiesProvided[commodityId]
              );
            }
          );
          Object.keys(resp.data.provided).forEach((item) => {
            providedCommoditiesData.push(resp.data.provided[item]);
          });
          let username = resp.data.loggedInUsername;
          let cartSize = resp.data.cartSize;

          this.setState(
            {
              providerInfo: {
                providerId: resp.data.info.id,
                providerName: resp.data.info.name,
                providerRegistryDate: resp.data.sinceYear,
                providerCommoditiesNum: resp.data.info.commoditiesNum,
                providerAvgCommoditiesRate: resp.data.info.avgCommoditiesRate,
                commoditiesProvidedID: providedCommoditiesId,
                providerImage: resp.data.info.image,
              },
              providedCommodities: providedCommoditiesData,
              username: username,
              cartItemsCount: cartSize,
            },
            () => {
              console.log(this.state);
            }
          );
        }
      })
      .catch((error) => {
        if (error.response.status === 401) {
          window.location.href = "http://localhost:3000/login";
        } else if (error.response.status === 404) {
          window.location.href = "http://localhost:3000/notfound";
        } else if (error.response.status === 400) {
          window.location.href = "http://localhost:3000/badrequest";
        }
      });
  }

  componentDidMount() {
    this.getBalootProvider();
    const title = `provider : ${this.state.providerInfo.providerName}`;
    document.title = title;
    // document.body.classList.add("bg-light");
  }

  render() {
    return (
      <div>
        <Nav
          username={this.state.username}
          cartItemsCount={this.state.cartItemsCount}
        />

        <div className="container">
          <div className="row mt-4 gy-4 ">
            <ProviderDetails providerDetails={this.state.providerInfo} />
          </div>
          <h3 className="text-brown pb-5 mt-10p">All provided commodities</h3>

          <div className="row mt-4 gy-4 product-container mb-5">
            {this.state.providedCommodities.map((item) => (
              <Item item={item} />
            ))}
          </div>
        </div>

        <Footer />
      </div>
    );
  }
}

export default Provider;
