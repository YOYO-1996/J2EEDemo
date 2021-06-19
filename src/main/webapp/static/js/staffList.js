$(function () {
    queryStaffList();
})

function queryStaffList() {
    $.ajax({
        url: '/Staff/queryStaffList',
        data: {startIndex:'0',queryCount:'10'},
        type: 'post',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            if (res.errorcode == 1) {
                $('.spinner-border').hide();
                Alert.show(res.errormsg)
            } else if (res.errorcode == 0) {
                $('.spinner-border').hide();
                // Alert.show(res.errormsg)
                var data = res.data;
                var tbodty = $('tbody');
                for (var i = 0; i < data.length; i++) {
                    var tr = $('<tr></tr>');
                    tr.attr('staId', data[i].staId);
                    tr.append('<td>' + data[i].staName + '</td>')
                        .append('<td>' + data[i].staSex + '</td>')
                        .append('<td>' + data[i].staRarity + '</td>')
                        .append('<td>' + data[i].staCareer + '</td>')
                        .append('<td>' + data[i].staFaction + '</td>')
                        .append('<td>' + data[i].staCost + '</td>')
                        .append('<td>' + data[i].staHealth + '</td>')
                        .append('<td>' + data[i].staAttackPower + '</td>')
                        .append('<td>' + data[i].staDefence + '</td>')
                        .append('<td>' + data[i].staSpellResistance + '</td>')
                        .append('<td>' + data[i].staAttackSpeed + '</td>')
                        .append('<td>' + data[i].staAvoidNum + '</td>')
                        .append('<td>' + data[i].staRedeploySpeed + '</td>');
                    tbodty.append(tr);
                }
            }
        }
    })
}

