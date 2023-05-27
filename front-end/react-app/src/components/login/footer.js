import "../../assets/styles/login-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";

class Footer extends Component {
  state = {};
  render() {
    return (
      <div>
        <footer className="container-fluid fixed-bottom footer">
          <div className="bg-dark">
            <div className="footer-txt">2023 @UT</div>
          </div>
        </footer>
      </div>
    );
  }
}

export default Footer;
