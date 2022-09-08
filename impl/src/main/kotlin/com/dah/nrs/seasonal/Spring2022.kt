package com.dah.nrs.seasonal

import com.dah.nrs.dsl.*
import com.dah.nrs.exts.*

fun DSLScope.Spring2022() {
    Entry {
        id = "A-MAL-48363"

        bestGirl = "Kotone Kazairo"

        Progress(Boredom.Dropped, 5)
        // weak character design, but the thing still looks cute
        Visual(VisualKind.Animated, 0.75, 0.3)
        KilledBy("F-VGMDB-7059", potential = 0.25, effect = 0.75)
    }
    Entry {
        id = "A-MAL-48415"

        bestGirl = "Ireena Litz de Olhyde"

        Progress(Boredom.Dropped, 2)
        Visual(VisualKind.Animated, 0.5, 0.2)
        KilledBy("F-VGMDB-7059", potential = 0.1, effect = 0.75)
    }
    Entry {
        id = "A-MAL-50265"

        ValidatorSuppress("dah-no-best-girl")

        // the "Chiếc thuyền ngoài xa" anime
        // some pasta inc
        // Đề bài: Trong tác phẩm "Chiếc thuyền ngoài xa", nhà văn Nguyễn Minh Châu đã viết:
        // "Mũi thuyền in một nét mơ hồ lòe nhòe vào bầu sương mù trắng như sữa có pha đôi chút màu hồng đỏ ánh mặt trời chiếu vào."
        // Bằng một bài văn ngắn anh/chị hãy nêu cảm nhận của mình về thông điệp ẩn giấu trong câu văn ấy. Từ đó, anh chị có những suy nghĩ gì về tài năng của tác giả?
        // Tác phẩm "Chiếc thuyền ngoài xa" của tác giả Nguyễn Minh Châu đã để lại rất nhiều những ý nghĩa cho bạn đọc, kể cả trong xã hội đã phát triển của thế kỷ XXI ngày nay. Tuy vậy, nhiều hình ảnh giàu sức gợi, ẩn giấu rất nhiều những suy tư và quan điểm của tác giả vẫn chưa được hiểu rõ. Dường như mỗi câu văn của tác phẩm là ẩn chứa những trí tuệ tràn đầy ý nghĩa. Trong bài viết ngắn này, tôi sẽ trình bày những suy ngẫm của mình về một câu văn như vậy:
        // "Mũi thuyền in một nét mơ hồ lòe nhòe vào bầu sương mù trắng như sữa có pha đôi chút màu hồng đỏ ánh mặt trời chiếu vào."
        // Khi nhân vật Phùng, người kể chuyện, sau biết bao ngày tháng "săn tìm" bức ảnh cuối cùng cho tập lịch đã tìm được một cảnh "đắt" trời cho của con thuyền trên biển, anh đã dùng câu văn trên để diễn tả hình ảnh mũi thuyền trong sương. Đối với nhiều bạn đọc, thậm chí những người thông thạo văn hoá hậu rst, câu văn này chỉ là một lời miêu tả đơn giản, tô điểm thêm cho cảnh vật. Nhưng khi đã đọc hết tác phẩm, hiểu rõ về "the chiếc thuyền ngoài xa analogy", bạn đọc mới nhận ra một nét nghĩa khác của câu.
        // Để hiểu được nét nghĩa này, như đã nói trên, bạn đọc cần phải đọc tác phẩm ít nhất một lần, biết được những tình tiết truyện. Vì thế tôi sẽ trình bày những tóm tắt cơ bản của tác phẩm ở đây: Nhân vật chính Phùng bị ông trưởng phòng bắt đi chụp ảnh cho tấm lịch năm mới. Sau khi phục kích mấy buổi sáng, Phùng đã chụp được một cảnh đẹp "đơn giản và toàn bích", nhưng sau đó chính mắt anh thấy được cảnh lão đàn ông từ trên thuyền thực hiện hành vi bạo lực với một người đàn bà cũng từ trên thuyền xuống. Một lần khác, Phùng cũng lại chứng kiến cảnh bạo lực này, nhưng do anh đã cạn ngăn, bị thương và khiến hai người kia phải ra toà. Ở đây, Phùng nhận ra sự thật rất đen tối của gia đình hai người này, hiểu rằng sự non choẹt của bản thân và trở về nhà với một bài học quý giá về nghệ thuật.
        // Hình ảnh chiếc thuyền đẹp "tuyệt đỉnh" mà tràn đầy bóng tối của cảnh bạo lực gia đình được những bạn đọc đúc kết lại thành "the chiếc thuyền ngoài xa analogy". Đó là một lời giải thích cho vì sao nhiều tác phẩm nghệ thuật dù vô cùng chất lượng, nhưng lại không xứng đáng được tung hô, coi trọng.
        // Trở lại với câu văn vừa trích trên, tưởng chừng đây chỉ là một câu giúp cho câu chuyện thêm dài, nhưng nó lại mang một nét nghĩa vô cùng đặc sắc. Thật vậy, đây chính là một hình ảnh ẩn dụ giàu sức sống của tác giả. Mũi thuyền ở đây là tượng trưng cho mũi của một con người, chính xác hơn là của một cô gái trong phim hoạt hình Nhật Bản (anime girl). Trong ngành công nghiệp này, có rất nhiều phong cách vẽ, nhưng ai cũng thấy được những quy tắc chung, như việc mũi của những nhân vật thường được vẽ rất mờ và nhỏ. Đó là lý do vì sao mà mũi thuyền "in một nét mơ hồ lòe nhòe". Chỉ qua hình ảnh này, bạn đọc đã có thể thấy được sự am hiểu của nhà văn với những loại hình văn hoá nước ngoài, cũng như lòng ham học tập, luôn cố gắng tiếp thu những tinh hoa của trí tuệ để ngày càng hoàn thiện bản thân.
        // Sau đó chính là lớp sương mù màu trắng sữa. Theo cùng chủ đề của những anime girl như trên, đây chính là ẩn dụ cho làn da trắng của cô gái ấy. Thật vậy, đây là sự liên tưởng vô cùng tự nhiên, dựa vào vị trí của mũi trên khuôn mặt. Điều này càng được làm rõ qua ẩn dụ những "màu hồng" được pha trên "bầu sương mù". Đó chính là đôi má hồng, hoặc cũng có thể là màu tóc hồng của cô gái này. Qua một câu văn, nhà văn đã khắc hoạ lên một bức chân dung của một cô gái, thông qua hình ảnh của một chiếc thuyền. Đây là một sự sáng tạo đỉnh cao của nhà văn, tinh hoa của trí tuệ của một tác phẩm, một kiệt tác nghệ thuật. Chưa từng có một bài viết nào trong kho tàng văn học Việt Nam, kể cả những tác phẩm của thiên tài văn chương Tô Hoài, có được một hình ảnh giàu ý nghĩa và ấn tượng đến vậy.
        // Nhưng bức chân dung này, giống như là bức tranh của cảnh vật chiếc thuyền, cũng chỉ là bề nổi của tảng băng. Ẩn chứa trong bức tranh ấy là những bóng tối, những đêm đen. Vào tháng 8 năm 1984, Nguyễn Minh Châu đã trả lời phỏng vấn về truyện ngắn này như sau:
        // "... Khi viết Chiếc thuyền ngoài xa, tôi để cạnh bản thảo của mình một bức fanart của 1 anime để khơi gợi cảm hứng sáng tác. Câu nói chửi rủa của lão đàn ông chính là những suy tư sâu thẳm nhất trong tôi khi nhìn vào bức fanart ấy…"
        // Thông điệp thực sự của câu văn nằm ở cái sắc hồng này. Đối với một người như Nguyễn Minh Châu, khi nhìn vào một bức tranh với trái tim đầy thù ghét, không thể nào để ý thấy sắc hồng của đôi má. Đây chắc hẳn là màu tóc hồng của cô gái ấy. Và dựa vào sự ghét cay ghét đắng của nhà văn, đây chính là Anya từ Spy x Family, hoặc Chika từ Love Is War. Qua đó bạn đọc cảm thấy xúc động trước những tư tưởng hiện đại của tác giả. Ông đã lên án sự nổi tiếng của những bộ anime "làm bằng tiền", từ đó đề cao những bộ như Re:Stage hay Show By Rock.
        // Nếu như Tô Hoài chỉ từ 5 chữ trọng bio của _kotachi_ đã viết nên một bản trường ca Tây Bắc, thì Nguyễn Minh Châu, với một câu văn hơn hai chục chữ, đã tạo nên được biết bao lớp nghĩa ẩn hiện với biết bao ý nghĩa. Những tác phẩm ấy của họ sẽ mãi được lưu giữ trong kho tàng văn học Việt Nam cũng như là rst lore, mãi mãi bất hủ trong lòng bạn đọc.
        Progress(Boredom.Dropped, 4)
        // unique art because manga, but chara design sucks
        // also fuck u boat shit
        Visual(VisualKind.Animated, 0.35, 0.5)
        KilledBy("F-VGMDB-7059", potential = 0.5, effect = 0.75)
    }
    Entry {
        id = "A-MAL-50273"
        bestGirl = "Maria Mizuse"

        Progress(Boredom.Completed)
        // unique art because manga, but chara design sucks (p2)
        Visual(VisualKind.Animated, 0.2, 0.5)
        KilledBy("F-VGMDB-7059", potential = 0.15, effect = 0.75)

        // nice plot desu
        AEI(5.0, Emotion.AP)
    }
}
