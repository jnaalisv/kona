import React from 'react'
import PropTypes from 'prop-types'

class AutoComplete extends React.Component {
    constructor(props) {
        super(props);
        this.state = {query: props.initialQuery, results:[]};
        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        event.preventDefault();
        const newQuery = event.target.value;
        this.setState({query: newQuery, results: []});
        if (newQuery && newQuery.length > 1) {
            this.props.searchCallback(newQuery).then(response => this.setState({results: response}));
        }
    }

    select(index) {
        const newSelection = {...this.state.results[index]};
        this.setState({query: newSelection.name, results: []});
        this.props.selectCallback(newSelection);
    }

    render() {
        return (
            <span>
                <input value={this.state.query} onChange={this.onChange}/>
                {
                    Object
                        .keys(this.state.results)
                        .map(index =>
                            <div
                                className={index === 0 ? 'autocomplete selected' : 'autocomplete'}
                                key={index}
                                onClick={() => this.select(index)}>
                                {this.props.renderResult(this.state.results[index])}
                            </div>
                        )
                }
            </span>
        )
    }
}

AutoComplete.propTypes = {
    searchCallback: PropTypes.func.isRequired,
    selectCallback: PropTypes.func.isRequired,
    renderResult: PropTypes.func.isRequired,
    initialQuery: PropTypes.string
};

export default AutoComplete;
