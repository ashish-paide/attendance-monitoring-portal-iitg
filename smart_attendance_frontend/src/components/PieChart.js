import React from 'react';
import { Pie } from 'react-chartjs-2';
import {
    Chart as ChartJs,
    ArcElement,
    Tooltip,
    Legend
} from 'chart.js'
import './PieChart.css';
ChartJs.register(
    ArcElement,
    Tooltip,
    Legend
);

const PieChart = ({ attendancePercentage, absencePercentage }) => {
  const data = {
    labels: ['Attended', 'Absent'],
    datasets: [
      {
        data: [attendancePercentage, absencePercentage],
        backgroundColor: ['rgba(75, 192, 192, 0.6)', 'rgba(255, 99, 132, 0.6)'],
      },
    ],
  };

  return (
    <div className='Pie-chart'>
       <Pie data={data} />
    </div>
  );
};

export default PieChart;
