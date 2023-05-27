import React, { Component } from "react";
import "../../assets/styles/user-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import accountImg from "../../assets/img/Vector (4).png";
import mailImg from "../../assets/img/Vector (5).png";
import calenderImg from "../../assets/img/Vector (6).png";
import locationImg from "../../assets/img/Vector (7).png";

class UserDetails extends Component {
  constructor(props) {
    super(props);
    this.state = {
      creditValue: 0,
    };
    this.handleCreditChange = this.handleCreditChange.bind(this);
  }

  handleCreditChange(e) {
    this.setState({ creditValue: e.target.value }, () => {
      // console.log(this.state.searchField);
    });
  }

  render() {
    return (
      <div class="row">
        <div class="container user-detail">
          <div class="row">
            <div class="col-6">
              <p>
                <img src={accountImg} class="user-info-item" alt="" />
                {this.props.AccountDetails.username}
              </p>
              <p>
                <img src={mailImg} class="user-info-item" alt="" />{" "}
                {this.props.AccountDetails.email}
              </p>
              <p>
                <img src={calenderImg} class="user-info-item" alt="" />{" "}
                {this.props.AccountDetails.birthDate}
              </p>
              <p>
                <img src={locationImg} class="user-info-item" alt="" />{" "}
                {this.props.AccountDetails.address}
              </p>
              <button class="btn logout-button">
                <a href="../logout" class="logout-text">
                  logout
                </a>
              </button>
            </div>
            <div class="col-6">
              <div class="container">
                <div class="row budget">
                  <h1 class="">${this.props.AccountDetails.credit}</h1>
                </div>
              </div>
              <form action="">
                <div class="row form-outline">
                  <input
                    type="number"
                    class="form-control amount-inbox"
                    id="creditAmount"
                    min="0"
                    placeholder="$Amount"
                    required
                    value={this.state.creditValue}
                    onChange={this.handleCreditChange}
                  ></input>
                </div>
                <div class="row">
                  <button
                    class="btn add-credit-button"
                    type="submit"
                    onClick={(event) =>
                      this.props.onAddCredit(event, this.state.creditValue)
                    }
                  >
                    Add more credit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default UserDetails;
