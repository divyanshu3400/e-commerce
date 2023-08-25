package com.cloudsect.myapplication.util

import com.cloudsect.myapplication.ui.home.BrandModel

class CategoryLists {

    companion object{
        fun getParentCategory(): List<BrandModel> {
            val brandModel = BrandModel()
            brandModel.brandImage ="https://thumbs.dreamstime.com/b/handsome-man-black-suit-white-shirt-posing-studio-attractive-guy-fashion-hairstyle-confident-man-short-beard-125019349.jpg"
            brandModel.brandId =1
            brandModel.brandTitle="Men's"

            val brandModel1 = BrandModel()
            brandModel1.brandId =2
            brandModel1.brandImage = "https://i.pinimg.com/550x/5b/72/42/5b72428637220bda71af81b3e2cd7a08.jpg"
            brandModel1.brandTitle="Kids"

            val brandModel2 = BrandModel()
            brandModel2.brandId =3
            brandModel2.brandImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQy8WfCuahrqYhCm2r9gIi2c1hBxBuABgKEfVPLH2QQYT3nJorWu-4bt73BVJdy02wtfW0&usqp=CAU"
            brandModel2.brandTitle="Women's"

            val brandModel3 = BrandModel()
            brandModel3.brandId =4
            brandModel3.brandTitle="Sports"
            brandModel3.brandImage = "https://media.npr.org/assets/img/2020/06/10/gettyimages-200199027-001_wide-3ff0f063a2bf1ab01550d3508c816bc43009d215-s1400-c100.jpg"

            val brandModel4 = BrandModel()
            brandModel4.brandId =5
            brandModel4.brandTitle="Appliances"
            brandModel4.brandImage = "https://media.istockphoto.com/id/1174598609/photo/set-of-contemporary-house-appliances-isolated-on-white.jpg?s=612x612&w=0&k=20&c=bBMILbCpLkhIxbL7sAAXaFOaFaSXFCt80ccHgl7iiWM="

            val brandModel5 = BrandModel()
            brandModel5.brandId =6
            brandModel5.brandTitle="Appliances"
            brandModel5.brandImage = "https://media.istockphoto.com/id/1174598609/photo/set-of-contemporary-house-appliances-isolated-on-white.jpg?s=612x612&w=0&k=20&c=bBMILbCpLkhIxbL7sAAXaFOaFaSXFCt80ccHgl7iiWM="


            return mutableListOf(
                brandModel, brandModel1, brandModel2, brandModel3,brandModel4
            )
        }
        fun getChildCategory(): List<BrandModel> {
            val brandModel = BrandModel()
            brandModel.brandImage ="https://images.pexels.com/photos/1043474/pexels-photo-1043474.jpeg?cs=srgb&dl=pexels-chloe-1043474.jpg&fm=jpg"
            brandModel.brandId =1
            brandModel.brandTitle="Top Wear"

            val brandModel1 = BrandModel()
            brandModel1.brandId =2
            brandModel1.brandImage = "https://m.media-amazon.com/images/I/41ovGiVH1TL._AC_UY1100_.jpg"
            brandModel1.brandTitle="Bottom Wear"

            val brandModel2 = BrandModel()
            brandModel2.brandId =3
            brandModel2.brandImage = "https://5.imimg.com/data5/YU/PD/WS/SELLER-61259432/men-s-winter-fashion-jacket.jpg"
            brandModel2.brandTitle="Winter Wear"

            val brandModel3 = BrandModel()
            brandModel3.brandId =4
            brandModel3.brandTitle="Cosmetics"
            brandModel3.brandImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-tUVk1L7dMyaWGWgxo6qal4I6sVhn0k3Nyw&usqp=CAU"

            val brandModel4 = BrandModel()
            brandModel4.brandId =5
            brandModel4.brandTitle="Foot Wear"
            brandModel4.brandImage = "https://media.istockphoto.com/id/685168074/photo/the-man-wears-shoes-tie-the-laces-on-the-shoes-mens-style-professions-to-prepare-for-work-to.jpg?s=612x612&w=0&k=20&c=OuVzFL8txXcZ1D4jfcIZ1YH1-5_7am7W0WrNWvzTPJM="

            val brandModel5 = BrandModel()
            brandModel5.brandId =6
            brandModel5.brandTitle="Watches"
            brandModel5.brandImage = "https://idestiny.in/wp-content/uploads/2022/09/Apple_Watch_SE_LTE_40mm_Starlight_Aluminum_Starlight_Sport_Band_PDP_Images_Position-1__en-IN.jpg"

            return mutableListOf(
                brandModel, brandModel1, brandModel2, brandModel3,brandModel4
            )
        }

    }
}