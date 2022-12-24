// Chakra imports
import {
  Box,
  Button,
  Flex,
  SimpleGrid
} from "@chakra-ui/react";
// Custom components
import Card from "components/card/Card.js";
import React from "react";
import {
  lineChartDataTotalSpent,
  lineChartOptionsTotalSpent,
} from "variables/charts";
import Form from 'react-bootstrap/Form';
import axios from 'axios';
import Chart from "react-apexcharts";
import Select from 'react-select'
import { format } from 'date-fns';
import { id } from "date-fns/locale";

//export default function TotalSpent(props) {
class TotalSpent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      chartData: lineChartDataTotalSpent,
      chartOptions: lineChartOptionsTotalSpent,
      param1: "weekly", //set all params to default value
      param2: "",
      param3: "",
      param4: "",
      param5: "",
      param6: "",
      param7:"",
      dropdownData: [
        { label: "Grapes", value: "grapes" },
        { label: "Mango", value: "mango" },
        { label: "Strawberry", value: "strawberry" },
      ]
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);

  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onListChange(e) {
    console.log(e);
    //this.setState({ [e.target.name]: e.target.value });
    e.target.value.map(item => {
      this.state.param6.push(e.value);
    })
  }

  onSubmit(e) {
    console.log(this.state.param4)
    console.log(format(new Date(this.state.param4), 'dd-MMM-yy'))
    console.log(this.state.param5)
    console.log(format(new Date(this.state.param5), 'dd-MMM-yy'))
    console.log(this.state.param1)

    // console.log(this.state.param3)
    
    e.preventDefault();  // Here we prevent the default browser behavior
    const url = 'http://localhost:8080/locationBestSeller';
    const x = [];
    const y = [];
    const state_id= [this.state.param2];
   // state_id.push(this.state.param2);
    console.log(state_id);
    axios.post(url,{"frequency":this.state.param1,"states":state_id, "startDate" : format(new Date(this.state.param4), 'dd-MMM-yy'), "endDate" : format(new Date(this.state.param5), 'dd-MMM-yy')})
    .then(response => {
      console.log("response", response)
      response.data.data.map(item => {
        const d = format(new Date(item.weekstart), 'dd-MMM-yy')
     
        x.push(d);
        y.push(item.numOrders)
      })

      const newChartData = [
        {
          name: "numOrders",
          data: y
        }
      ]

      const newChartOptions = {
        chart: {
          toolbar: {
            show: false,
          },
          dropShadow: {
            enabled: true,
            top: 13,
            left: 0,
            blur: 10,
            opacity: 0.1,
            color: "#4318FF",
          },
        },
        colors: ["#4318FF", "#39B8FF"],
        markers: {
          size: 0,
          colors: "white",
          strokeColors: "#7551FF",
          strokeWidth: 3,
          strokeOpacity: 0.9,
          strokeDashArray: 0,
          fillOpacity: 1,
          discrete: [],
          shape: "circle",
          radius: 2,
          offsetX: 0,
          offsetY: 0,
          showNullDataPoints: true,
        },
        tooltip: {
          theme: "dark",
        },
        dataLabels: {
          enabled: false,
        },
        stroke: {
          curve: "smooth",
          type: "line",
        },
        xaxis: {
          type: "string",
          categories: x,
          labels: {
            style: {
              colors: "#A3AED0",
              fontSize: "12px",
              fontWeight: "500",
            },
          },
          axisBorder: {
            show: false,
          },
          axisTicks: {
            show: false,
          },
        },
        yaxis: {
          show: true,
        },
        legend: {
          show: false,
        },
        grid: {
          show: true,
          column: {
            color: ["#7551FF", "#39B8FF"],
            opacity: 0.5,
          },
        },
        color: ["#7551FF", "#39B8FF"],
      }

      this.setState({ chartData: newChartData });
      this.setState({ chartOptions: newChartOptions });

    }, [])
  }

  render() {
    return (
      <Card
        justifyContent='center'
        align='center'
        direction='column'
        w='100%'
        mb='0px'>
        <Form>
          <SimpleGrid columns={6} gap='10px' mb='10px'>
            <Form.Select name="param1" value={this.state.param1} onChange={this.onChange}>
              <option value="weekly"> Weekly </option>
              <option value="monthly"> Monthly </option>
            </Form.Select>
           
            <Form.Select name="param2" value={this.state.param2} onChange={this.onChange}>
            <option value="PE"></option>PE
            <option value="MT">MT</option>
            <option value="MS">MS</option>
            <option value="BA">BA</option>
            <option value="GO">GO</option>
            <option value="RR">RR</option>
            <option value="MG">MG</option>
            <option value="DF">DF</option>
            <option value="AM">AM</option>
            <option value="PR">PR</option>
            <option value="SC">SC</option>
            <option value="CEy">CE</option>
            <option value="AP">AP</option>
            <option value="RS">RS</option>
            <option value="RJ">RJ</option>
            <option value="RO">RO</option>
            <option value="PB">PB</option>
            <option value="PI">PI</option>
            <option value="SE">SE</option>
            <option value="AL">AL</option>
            <option value="PA">PA</option>
            <option value="AC">AC</option>
            <option value="SP">SP</option>
            <option value="ES">ES</option>
            <option value="TO">TO</option>
            <option value="RN">RN</option>
            <option value="MA">MA</option>
            
            </Form.Select>
            {/* <Form.Select name="param3" value={this.state.param3} onChange={this.onChange}>
              <option value="bebes"> bebes </option>
              <option value="bebes"> bebes </option>
            </Form.Select> */}
            {/* <Form.Select name="param7" value={this.state.param7} onChange={this.onChange}>
              <option value="SP"> SP </option>
          
            </Form.Select> */}
            <Form.Control type="date" name="param4" value={this.state.param4} onChange={this.onChange} />
            <Form.Control type="date" name="param5" value={this.state.param5} onChange={this.onChange} />
            {/* <Select options={this.state.dropdownData} /> */}
            <Button bg='black' color={"white"} type="submit" onClick={this.onSubmit}>Submit</Button>
          </SimpleGrid>
        </Form>
        <Flex w='100%' flexDirection={{ base: "column", lg: "row" }}>
          <Box minH='260px' minW='75%' mt='auto'>
            <Chart
              options={this.state.chartOptions}
              series={this.state.chartData}
              type='line'
              width='100%'
              height='100%'
            />
          </Box>
        </Flex>
      </Card>
    );
  }
}

export default TotalSpent;