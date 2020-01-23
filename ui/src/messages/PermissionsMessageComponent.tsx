import React, {Component} from 'react'; // let's also import Component

import axios, {AxiosResponse} from 'axios';


type PermissionsMessageComponentState = {
    ruleInfo: any;
};
export class PermissionsMessageComponent extends Component<{}, PermissionsMessageComponentState> {

    constructor(props: {}) {
        super(props);
        this.state = {
            ruleInfo: ""
        };
    }

    componentDidMount(): void {
        axios.get("/rule/info")
            .then((value: AxiosResponse) => {
                this.setState({...this.state, ruleInfo: JSON.stringify(value.data, null, 4)});
            })
			.catch((err:any )=>{
				alert('Something went wrong, please check application console');
			})

    }


    render() {
        return (
            <div className="message">
                <div className="message-body">
                    Below is the list permissions needed for each rule.
                </div>
                <pre>
				<code className="language-json" data-lang="json">{this.state.ruleInfo}</code>
			</pre>
            </div>
        );
    }
}