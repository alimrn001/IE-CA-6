import "../../assets/styles/home-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";

class Footer extends Component {
  state = {};
  render() {
    return (
      <div>
        <footer className="footer">
          <div className="footer-txt">2023 @UT</div>
        </footer>
      </div>
    );
  }
}

export default Footer;
