import "../../assets/styles/product-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";

class ProductDetails extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ratingValue: 0,
    };
    this.handleRatingChange = this.handleRatingChange.bind(this);
  }

  handleRatingChange(e) {
    this.setState({ ratingValue: e.target.value }, () => {
      // console.log(this.state.searchField);
    });
  }

  render() {
    return (
      <div>
        <div className="padding">
          <h2 className="product-title">
            {this.props.ProductDetails.productName}
          </h2>
        </div>

        <div className="d-flex justify-content-between padding">
          <div className="in-stock text-red">
            {this.props.ProductDetails.countLeft} left in stock
          </div>
          <div className="margin-right">
            <img
              src={this.props.ProductDetails.starImgURL}
              alt="star"
              className="rating-star-img"
            />
            <span className="rate-score">
              {this.props.ProductDetails.rateScore}
            </span>
            <span className="rate-count">
              ({this.props.ProductDetails.rateCount})
            </span>
          </div>
        </div>

        <div className="padding">
          <p>
            by{" "}
            <a href={"../providers/" + this.props.ProductDetails.providerId}>
              {this.props.ProductDetails.providerName}
            </a>
          </p>
        </div>

        <p className="text-brown padding">Category(s)</p>

        <div className="padding">
          <ul className="text-brown">
            {this.props.ProductDetails.categories.map((item) => (
              <li> {item} </li>
            ))}
          </ul>
        </div>

        <div className="d-flex justify-content-between margin-right bg-white p-2 shadow-sm card-section">
          <div className="text-brown padding-left margin-top">
            <p className="font-800">{this.props.ProductDetails.price}$</p>
          </div>
          <div className="">
            <button
              className="btn add-cart-main-button"
              type="submit"
              onClick={(event) => this.props.onAddToBuyList(event)}
            >
              add to card
            </button>
          </div>
        </div>

        <div className="d-flex justify-content-between margin-right p-2">
          <div className="">
            <p className="margin-bottom">rate now</p>
            <p>
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
              <img
                src={this.props.ProductDetails.starImgURL}
                className="rating-star-img-2"
                alt=""
              />
            </p>
          </div>
          <div className="">
            <input
              type="number"
              min={0}
              max={10}
              className="form-control"
              placeholder="Rating"
              required
              value={this.state.ratingValue}
              onChange={this.handleRatingChange}
            />
            <button
              className="btn rate-submit-button"
              type="submit"
              onClick={(event) =>
                this.props.onAddRating(event, this.state.ratingValue)
              }
            >
              submit
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default ProductDetails;
