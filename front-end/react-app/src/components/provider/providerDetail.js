import React, { Component } from "react";

import "../../assets/styles/provider-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";

class ProviderDetail extends Component {
  render() {
    return (
      <div className="mt-100 d-flex align-items-center justify-content-center">
        <div>
          <img
            className="img-fluid"
            src={this.props.providerDetails.providerImage}
            alt="provider picture"
          />
          <div className="provider-since-date-container d-flex justify-content-end">
            <p className="text-brown provider-since-date">
              since {this.props.providerDetails.providerRegistryDate}
            </p>
          </div>
          <h3 className="text-brown">
            {this.props.providerDetails.providerName}
          </h3>
        </div>
      </div>
    );
  }
}

export default ProviderDetail;
