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
import { format } from 'date-fns';

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
      param4: "07-JAN-2017",
      param5: "08-JAN-2018",
      param6: [],
      // dropdownData: [
      //   { label: "Grapes", value: "grapes" },
      //   { label: "Mango", value: "mango" },
      //   { label: "Strawberry", value: "strawberry" },
      // ]
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
    e.preventDefault();  // Here we prevent the default browser behavior


    const url = 'http://localhost:8080/newCustomers';
    const x = [];
    const y = [];
    axios.post(url,{"startDate" : format(new Date(this.state.param4), 'dd-MMM-yy'), "endDate" : format(new Date(this.state.param5), 'dd-MMM-yy'), "frequency" : this.state.param1})
    .then(response => {
      console.log("response", response)
      response.data.data.map(item => {
        
        const d = format(new Date(item.frequencyStart), 'dd-MMM-yy');   
        x.push(d);
        y.push(item.newCustomers)
      })

      const newChartData = [
        {
          name: "Number of new Customer",
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
          <SimpleGrid columns={4} gap='10px' mb='10px'>
         {/* dropdown */}
            <Form.Select name="param1" value={this.state.param1} onChange={this.onChange}>
              <option value="weekly"> Weekly </option>
              <option value="monthly"> Monthly </option>
            </Form.Select>
           
            {/* date */}
            <Form.Control type="date" name="param4" value={this.state.param4} onChange={this.onChange} />

            <Form.Control type="date" name="param5" value={this.state.param5} onChange={this.onChange} />
           
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